package org.objecttrouve.nexttext

internal class NormalizedCriDistance {

    internal fun normalizedCriDistance(criCounts1: CriCounts, criCounts2: CriCounts): Double {
        return AbsoluteCriDistance().distance(criCounts1, criCounts2).toDouble() / (criCounts1.textLength() + criCounts2.textLength()).toDouble()
    }
}