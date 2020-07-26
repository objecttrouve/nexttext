/**
 * Released under the terms of the Apache License 2.0.
 * Copyright (c) 2020 objecttrouve.org <un.object.trouve@gmail.com>.
 */
package org.objecttrouve.nexttext

import java.lang.Integer.max
import kotlin.math.abs

internal class AbsoluteCriDistance {

    internal fun distance(criCounts1: CriCounts, criCounts2: CriCounts): Int{
        val maxIntervalLength = max(criCounts1.maxIntervalLength(), criCounts2.maxIntervalLength())
        var distance = 0
        for(i in 0..criCounts1.size()){
            for(j in 0..maxIntervalLength){
                val count1 = getCount(i, j , criCounts1)
                val count2 = getCount(i, j , criCounts2)
                distance += abs(count1-count2)
            }
        }
        return distance
    }

    private fun getCount(i: Int, j: Int, criCounts : CriCounts): Int{
        return if (criCounts.maxIntervalLength() >= j) {
            criCounts.counts()[i][j]
        } else {
            0
        }
    }
}