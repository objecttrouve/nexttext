package org.objecttrouve.nexttext

class Config {

}

class NextText private constructor (
        val criCounter: CriCounter
){
    data class Builder(
            var minCodePoint : Int = 0,
            var maxCodePoint: Int = 127) {

        fun withMinCodePoint(minCodePoint: Int) = apply {this.minCodePoint =  minCodePoint}
        fun withMaxCodePoint(maxCodePoint: Int) = apply {this.maxCodePoint =  maxCodePoint}

        fun build() = NextText(CriCounter(minCodePoint, maxCodePoint))
    }

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
