package com.github.acschil.sorting

class CountingSort {
    /**
     * Counting sort. O(k+n) running time; linear when k is O(n).
     *
     * @param list of integers A[0...n] where A[i] ∈ {0, 1, . . . , k} for all i
     *
     * ref. https://alg12.wikischolars.columbia.edu/file/view/RADIX.pdf/299792868/RADIX.pdf
     */
    static int[] countingSort(int[] input, int k) {
        int[] counts = new int[k + 1]
        input.each {
            counts[it]++
        }
        (1..k).each {
            counts[it] += counts[it - 1]
        }
        int[] output = new int[input.length]
        ((input.size() - 1)..0).each {
            output[--counts[input[it]]] = input[it]
        }
        return output
    }
}
