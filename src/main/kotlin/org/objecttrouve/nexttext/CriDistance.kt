package org.objecttrouve.nexttext

import java.lang.Integer.max

class CriDistance {
    fun distance(criCounts1: CriCounts, criCounts2: CriCounts): Int{
        val maxIntervalLength = max(criCounts1.maxIntervalLength(), criCounts2.maxIntervalLength())
        var distance = 0
        for(i in 0..criCounts1.size()){
            for(j in 0..maxIntervalLength){
                val count1 = getCount(i, j , criCounts1)
                val count2 = getCount(i, j , criCounts2)
                distance += Math.abs(count1-count2)
            }
        }
        return distance
    }

    private fun getCount(i: Int, j: Int, criCounts : CriCounts): Int{
        if (criCounts.maxIntervalLength() >= j) {
          return criCounts.counts()[i][j]
        } else {
            return 0
        }
    }
}