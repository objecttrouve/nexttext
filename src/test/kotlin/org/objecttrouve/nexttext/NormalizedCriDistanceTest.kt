package org.objecttrouve.nexttext

import org.junit.Ignore
import org.junit.Test
import org.hamcrest.Matchers
import org.junit.Assert
import org.objecttrouve.testing.matchers.ConvenientMatchers

@Ignore("Manual usage.")
class NormalizedCriDistanceTest {

    private val counter = CriCounter(0, 127)
    private val scorer = NormalizedCriDistance()


    @Test
    fun normalizedCriDistance__two_empty_strings() {
        val text1 = ""
        val text2 = ""
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(Double.NaN))
    }

    @Test
    fun normalizedCriDistance__A_A() {
        val text1 = "A"
        val text2 = "A"
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(0.0))
    }

    @Test
    fun normalizedCriDistance__A_empty() {
        val text1 = "A"
        val text2 = ""
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(1.0))
    }

    @Test
    fun normalizedCriDistance__A_B() {
        val text1 = "A"
        val text2 = "B"
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(1.0))
    }

    @Test
    fun normalizedCriDistance__AA_BB() {
        val text1 = "AA"
        val text2 = "BB"
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(1.0))
    }

    @Test
    fun normalizedCriDistance__AB_BA() {
        val text1 = "AB"
        val text2 = "BA"
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(1.0))
    }

    @Test
    fun normalizedCriDistance__ABA_BAB() {
        val text1 = "ABA"
        val text2 = "BAB"
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(1.0))
    }

    @Test
    fun normalizedCriDistance__ABAB_BABA() {
        val text1 = "ABAB"
        val text2 = "BABA"
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(0.5))
    }

    @Test
    fun normalizedCriDistance__ABAB_CDCD() {
        val text1 = "ABAB"
        val text2 = "CDCD"
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(1.0))
    }

    @Test
    fun normalizedCriDistance__ABAB_empty() {
        val text1 = "ABAB"
        val text2 = ""
        val c1 = counter.criCounts(text1)
        val c2 = counter.criCounts(text2)

        val score = scorer.normalizedCriDistance(c1, c2)

        Assert.assertThat(score, Matchers.`is`(1.0))
    }



    @Test
    fun normalizedCriDistance__sort_by_distance() {
        val target = "Text here! With e's repeating..."
        val c1 = counter.criCounts(target)

        val candidates : MutableList<String> = mutableListOf(
                "Longer text here! With t's repeating...",
                "Text here! With e's repeating... But much much much much much much longer!! Really!",
                "Nope!",
                "Text here! With e's repeating...",
                "Text there! With e's repeating...",
                "Text here! With e's repeating... But much much much much much much longer!! Really! Shouldn't the length difference really make more difference?",
                "Not much in common, at all."
        )

        candidates.sortWith(compareBy {scorer.normalizedCriDistance(c1, counter.criCounts(it))})

        Assert.assertThat(candidates, Matchers.`is`(ConvenientMatchers.anIterableOf(String::class.java)
                .exactly()
                .ordered()
                .withItems(
                        "Text here! With e's repeating...",
                        "Text here! With e's repeating... But much much much much much much longer!! Really!",
                        "Text there! With e's repeating...",
                        "Longer text here! With t's repeating...",
                        "Text here! With e's repeating... But much much much much much much longer!! Really! Shouldn't the length difference really make more difference?",
                        "Not much in common, at all.",
                        "Nope!"
                )
        ))

    }

}