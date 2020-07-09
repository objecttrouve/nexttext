package org.objecttrouve.nexttext
/**
 * CRI distance calculator.
 *
 * Configurable with a range of code points [`minCodePoint`..`maxCodePoint`].
 * Only code points within that range are considered.
 */
class NextText private constructor (
        private val criCounter: CriCounter
){

    /**
     * Builder for `NextText` instances.
     */
    data class Builder(
            private var minCodePoint : Int = 0,
            private var maxCodePoint: Int = 127) {

        /**
         * Sets the lower bound on the range of code points to operate on (inclusice).
         *
         * Defaults to 0.
         *
         * @param minCodePoint lower bound on code point range
         */
        fun withMinCodePoint(minCodePoint: Int) = apply { this.minCodePoint =  posInt(minCodePoint) }

        /**
         * Sets the upper bound on the range of code points to operate on (inclusice).
         *
         * Defaults to 127.
         *
         * @param maxCodePoint upper bound on code point range
         */
        fun withMaxCodePoint(maxCodePoint: Int) = apply { this.maxCodePoint =  posInt(maxCodePoint) }

        private fun posInt(intParam: Int): Int {
            require(intParam >= 0) {
                "Parameter must be a positive integer."
            }
            return intParam
        }

        fun build() = NextText(CriCounter(minCodePoint, maxCodePoint))
    }

    /**
     * Calculates the normalized CRI distance between `text1` and `text2`.
     *
     * A return value of 0.0 means the input strings are identical.
     * A return value of 1.0 means they have nothing in common.
     * ("Nothing" basically means no subsequences longer than 1.)
     *
     * @param text1 first text
     * @param text1 second text
     * @return value between 0.0 and 1.0
     */
    fun criDistance(text1 : String, text2: String): Double {
        if (text1.isEmpty()){
            return if (text2.isEmpty()) 0.0 else 1.0
        }
        if (text2.isEmpty()){
            return if (text1.isEmpty()) 0.0 else 1.0
        }

        return NormalizedCriDistance().normalizedCriDistance(criCounter.criCounts(text1), criCounter.criCounts(text2))
    }
}
