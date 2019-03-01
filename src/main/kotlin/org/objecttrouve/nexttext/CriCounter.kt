package org.objecttrouve.nexttext

import java.lang.Integer.max
import java.util.*

class CriCounter(
        private val minCodePoint: Int = 0,
        private val maxCodePoint: Int = 127
) {
    init {
        if (minCodePoint < 0 || maxCodePoint < minCodePoint) {
            throw IllegalArgumentException("Invalid code point. " +
                    "The 'minCodePoint' must be >= 0, was $minCodePoint." +
                    "The 'maxCodePoint' must be >= 'minCodePoint', was $maxCodePoint.")
        }
    }
    fun criCounts(text: String): CriCounts {
        val nrOfSymbols = maxCodePoint - minCodePoint+1
        val lastPositions = IntArray(nrOfSymbols)
        val intervalsBySymbol = Array(nrOfSymbols) { LinkedList<Int>()}
        var maxIntervalLength = 0
        for (i in text.indices) {
            val codePoint = Character.codePointAt(text, i)
            val ix = ix(codePoint)
            if (ix >= 0 && ix < intervalsBySymbol.size) {
                val intervalLength = i - lastPositions[ix]
                lastPositions[ix] = i
                intervalsBySymbol[ix].add(intervalLength)
                maxIntervalLength = max(maxIntervalLength, intervalLength)
            }
        }
        val intervalCountsBySymbol = Array(nrOfSymbols) { IntArray(maxIntervalLength+1)}
        for (i in intervalsBySymbol.indices){
            for (j in intervalsBySymbol[i].indices){
                val intervalLength = intervalsBySymbol[i][j]
                intervalCountsBySymbol[i][intervalLength]++
            }
        }
      return CriCounts(minCodePoint, maxCodePoint, maxIntervalLength, intervalCountsBySymbol)
    }

    private fun ix(i: Int): Int {
        return i - minCodePoint
    }
}