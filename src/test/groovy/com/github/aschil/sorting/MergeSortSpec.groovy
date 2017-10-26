package com.github.aschil.sorting

import spock.lang.Specification
import spock.lang.Unroll

class MergeSortSpec extends Specification {

    def "mergesorts empty collection"() {
        setup:
        List input = []

        when:
        MergeSort.sort(input)

        then:
        input == []
    }

    @Unroll
    def "mergesorts list of numbers"() {
        when:
        MergeSort.sort(input)

        then:
        input == output

        where:
        input                                          | output
        [1, 2, 3]                                      | [1, 2, 3]
        [1, 5, 6, 9, 2]                                | [1, 2, 5, 6, 9]
        [5]                                            | [5]
        [1, 2, 2, 2, 2, 3]                             | [1, 2, 2, 2, 2, 3]
        [2.0, 4.8, 3.995, 3.99999, 1, 9.2357, 3.14159] | [1, 2.0, 3.14159, 3.995, 3.99999, 4.8, 9.2357]
        [1, -1, 0]                                     | [-1, 0, 1]
    }

    @Unroll
    def "mergesorts list of strings"() {
        when:
        MergeSort.sort(input)

        then:
        input == output

        where:
        input                   | output
        ["A", "C", "D", "B"]    | ["A", "B", "C", "D"]
        ["A", "B", "C"]         | ["A", "B", "C"]
        ["B", "a", "A", "b"]    | ["A", "B", "a", "b"]
        ["dog", "cat", "doggo"] | ["cat", "dog", "doggo"]
    }
}
