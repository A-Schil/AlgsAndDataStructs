package com.github.aschil.sorting

import spock.lang.Specification
import spock.lang.Unroll

class MergeSortSpec extends Specification {
    @Unroll
    def "mergesort list of int"() {
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
        [1, -1, 0]                                     | [-1, 0, 1]
    }
}
