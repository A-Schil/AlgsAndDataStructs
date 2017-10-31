package com.github.acschil.sorting

import spock.lang.Specification
import spock.lang.Unroll

class CountingSortSpec extends Specification {
    @Unroll
    def "sorts inout where 0 <= input[i] <= 9"() {
        when:
        CountingSort.countingSort(input as int[], k)

        then:
        input == expectedOutput

        where:
        input        | k || expectedOutput
        [0, 1, 2, 3] | 9 || [0, 1, 2, 3]
        [2, 1, 3, 0] | 9 || [0, 1, 2, 3]
        [0, 0, 1, 1] | 9 || [0, 0, 1, 1]
        [1, 1, 1, 0] | 9 || [0, 1, 1, 1]
        [5, 9, 4, 8] | 9 || [4, 5, 8, 9]
    }

    def "sorts input size of 0"() {
        setup:
        int[] input = []

        when:
        CountingSort.countingSort(input as int[], 9)

        then:
        input == [] as int[]
    }

    def "sorts input size of 1"() {
        setup:
        int[] input = [0]

        when:
        CountingSort.countingSort(input as int[], 9)

        then:
        input == [0] as int[]
    }
}
