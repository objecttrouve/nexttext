package org.objecttrouve.nexttext

data class CriCounts(
        private val minCodePoint: Int,
        private val maxCodePoint: Int,
        private val maxIntervalLength: Int,
        private val textLength: Int,
        private val intervalCounts: Array<IntArray>
) {
    fun counts(): Array<IntArray> {
        return intervalCounts
    }

    fun size(): Int {
        return maxCodePoint - minCodePoint
    }

    fun maxIntervalLength(): Int {
        return maxIntervalLength
    }

    fun textLength(): Int {
        return textLength
    }

}