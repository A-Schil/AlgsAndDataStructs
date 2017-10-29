package com.github.acschil.sorting

import spock.lang.Specification

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
}
