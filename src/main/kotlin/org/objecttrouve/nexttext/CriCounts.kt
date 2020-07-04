package org.objecttrouve.nexttext

internal data class CriCounts(
        private val minCodePoint: Int,
        private val maxCodePoint: Int,
        private val maxIntervalLength: Int,
        private val textLength: Int,
        private val intervalCounts: Array<IntArray>
) {
    internal fun counts(): Array<IntArray> {
        return intervalCounts
    }

    internal fun size(): Int {
        return maxCodePoint - minCodePoint
    }

    internal fun maxIntervalLength(): Int {
        return maxIntervalLength
    }

    internal fun textLength(): Int {
        return textLength
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CriCounts

        if (minCodePoint != other.minCodePoint) return false
        if (maxCodePoint != other.maxCodePoint) return false
        if (maxIntervalLength != other.maxIntervalLength) return false
        if (textLength != other.textLength) return false
        if (!intervalCounts.contentDeepEquals(other.intervalCounts)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = minCodePoint
        result = 31 * result + maxCodePoint
        result = 31 * result + maxIntervalLength
        result = 31 * result + textLength
        result = 31 * result + intervalCounts.contentDeepHashCode()
        return result
    }

}