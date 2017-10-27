package com.github.acschil.sorting

import spock.lang.Specification

class SortingUtilitiesTest extends Specification {
    def "exchange"() {
        when:
        SortingUtilities.exchange(list, i1, i2)

        then:
        list == expected

        where:
        list          | i1 | i2 || expected
        [1, 2]        | 0  | 1  || [2, 1]
        [1, 2]        | 1  | 0  || [2, 1]
        ['a', 'b']    | 0  | 1  || ['b', 'a']
        [true, false] | 0  | 1  || [false, true]
        [1, 2, 3]     | 0  | 2  || [3, 2, 1]
        [1, 2, 3]     | 0  | 1  || [2, 1, 3]
        [1, 2, 3]     | 1  | 2  || [1, 3, 2]
    }

    def "exchange - index out of bounds"() {
        setup:
        List list = [1, 2, 3]

        when:
        SortingUtilities.exchange(list, 0, 4)

        then:
        println list
        thrown(IndexOutOfBoundsException)
    }
}
