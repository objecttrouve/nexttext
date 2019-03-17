package org.objecttrouve.nexttext

class CriScore {

    fun score(criCounts1: CriCounts, criCounts2: CriCounts): Double {
        return CriDistance().distance(criCounts1, criCounts2).toDouble() / ((criCounts1.textLength()+criCounts2.textLength()).toDouble()/2)
    }
}