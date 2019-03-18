package org.objecttrouve.nexttext

import com.google.common.cache.CacheBuilder
import org.junit.Ignore
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import java.util.concurrent.TimeUnit



@Ignore("Manual usage.")
class CriScoreExperiment {

    private val counter = CriCounter(0, 127)
    private val calc = CriScore()
    private val downloads = Paths.get(System.getProperty("user.home")).resolve("Downloads/nexttext/wiki").toAbsolutePath()
    val contentCache: LoadingCache<Path, String> = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(
                    object : CacheLoader<Path, String>() {
                        override fun load(path: Path): String {
                            return read(path)
                        }
                    })


    @Test
    fun download() {
        val wiki = Wiki("https://fr.wikipedia.org")
        val pages = 500
        val revisions = 250
        val abc = CharArray(26) { (it + 97).toChar() }
        for (letter in abc) {
            val allPages = wiki.getAllPages(letter.toString(), pages)
            for (i in allPages.indices) {
                val pageTitle = wiki.normalizeTitle(allPages[i].title)
                val pageDir = downloads.resolve(pageTitle)
                val revs = wiki.getPageRevisions(pageTitle, revisions, "content|ids", "")
                if (revs.isNotEmpty()) {
                    Files.createDirectories(pageDir)
                    for (j in revs.indices) {
                        val revDir = pageDir.resolve(j.toString())
                        Files.createDirectories(revDir)
                        println("Download: $letter: $i,$j ($pageTitle)")
                        val rev = revs[j]
                        val revFile = revDir.resolve(pageTitle)
                        Files.write(revFile, rev.value.toByteArray())
                    }
                }
            }
        }
    }

    @Test
    fun run() {
        val allPages = Files.list(downloads).collect(Collectors.toList())
        val scoresSameVersions = Array(allPages.size) { DoubleArray(500) { -1.0 } }
        for (i in allPages.indices) {
            println("Same: $i")
            val pagePath = allPages[i]
            val pageTitle = pagePath.fileName
            val revs = Files.list(pagePath).collect(Collectors.toList())
            if (revs.size > 1) {
                for (j in revs.indices) {
                    val k = j + 1
                    if (k < revs.size) {
                        val score = getScore(contentCache.get(revs[j].resolve(pageTitle)), contentCache.get(revs[k].resolve(pageTitle)))
                        scoresSameVersions[i][j] = score
                    }
                }
            }
        }
        val scoresAmongPages = Array(allPages.size) { DoubleArray(allPages.size) { -1.0 } }
        for (i in allPages.indices) {
            println("Cross: $i")
            val contentI = contentCache.get(lastRevision(allPages, i))
            for (j in allPages.indices) {
                val contentJ = contentCache.get(lastRevision(allPages, j))
                val score = getScore(contentI, contentJ)
                scoresAmongPages[i][j] = score
            }
        }
        var goodValues = 0
        var badValues = 0
        for (i in allPages.indices) {
            println("Eval: $i")
            for (j in allPages.indices) {
                val interPageScore = scoresAmongPages[i][j]
                for (k in scoresSameVersions[i].indices) {
                    val innerPageScore = scoresSameVersions[i][k]
                    if (innerPageScore > -1.0) {
                        if (interPageScore < innerPageScore) {
                            badValues++
                        } else {
                            goodValues++
                        }
                    }
                }
            }
        }

        println("Good: $goodValues. Bad: $badValues. Ratio: ${(goodValues.toDouble() * 100) / (goodValues + badValues)}%.")

    }

    private fun lastRevision(allPages: MutableList<Path>, ix: Int) =
            allPages[ix].resolve("0").resolve(allPages[ix].fileName)

    private fun read(path: Path?) = String(Files.readAllBytes(path))

    private fun getScore(text1: String, text2: String): Double {
        val criCountsJ = counter.criCounts(text1)
        val criCountsK = counter.criCounts(text2)
        return calc.score(criCountsJ, criCountsK)
    }
}