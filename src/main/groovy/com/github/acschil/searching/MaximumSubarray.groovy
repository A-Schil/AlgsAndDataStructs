package com.github.acschil.searching

class MaximumSubarray {
    static int kadanes(int[] array) {
        int maxEndingHere = array[0]
        int maxSoFar = maxEndingHere
        (1..(array.length - 1)).each { int i ->
            maxEndingHere = Math.max(array[i], maxEndingHere + array[i])
            maxSoFar = Math.max(maxSoFar, maxEndingHere)
        }
        return maxSoFar
    }
}
