package com.github.acschil.sorting

class RadixSort {
    private static int max(int[] array) {
        if (array.length < 1) {
            throw new NoSuchElementException()
        }
        int max = Integer.MIN_VALUE
        array.each {
            if (it > max) {
                max = it
            }
        }
        return max
    }
//
//    private static void countSort(int[] array, int exponent) {
//
//    }
}
