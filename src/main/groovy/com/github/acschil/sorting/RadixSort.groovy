package com.github.acschil.sorting

class RadixSort {

    static void radixSort(int[] array) {
        int max = max(array)

        for (int exponent = 1; (max/exponent) > 1; exponent *= 10) {
            countSort(array, exponent)
        }

        println array
    }

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

    private static void countSort(int[] array, int exponent) {
        int[] counts = new int[10]
        array.each {
            int index = (int)(it / exponent) % 10
            counts[index]++
        }
        (1..9).each {
            counts[it] += counts[it - 1]
        }
        int[] output = new int[array.length]
        ((array.size() - 1)..0).each {
            int index = (int)(array[it] / exponent) % 10
            output[--counts[index]] = array[it]
        }
        (0..(array.length - 1)).each {
            array[it] = output[it]
        }
    }
}
