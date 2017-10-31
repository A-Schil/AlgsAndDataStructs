package com.github.acschil.sorting

import spock.lang.Specification
import spock.lang.Unroll

class RadixSortSpec extends Specification {
    def "max on array of size 0"() {
        when:
        RadixSort.max([] as int[])

        then:
        thrown(NoSuchElementException)
    }

    def "max"() {
        expect:
        RadixSort.max(input as int[]) == expectedMax

        where:
        input           || expectedMax
        [0]             || 0
        [1, 2, 3, 4, 5] || 5
        [9, 9, 9]       || 9
        [999999999, 9]  || 999999999
    }

    def "max of min value"() {
        setup:
        int minInt = Integer.MIN_VALUE
        int[] input = new int[1]
        input[0] = minInt

        expect:
        RadixSort.max(input) == minInt
    }

    @Unroll
    def "radixSort"() {
        setup:
        int[] array = input as int[]

        when:
        RadixSort.radixSort(array)

        then:
        array == expectedOutput as int[]

        where:
        input                    || expectedOutput
        [102, 256, 174, 721]     || [102, 174, 256, 721]
        [2, 1, 3, 0]             || [0, 1, 2, 3]
        [967, 1_024, 10, 99_736] || [10, 967, 1_024, 99_736]
    }
}
