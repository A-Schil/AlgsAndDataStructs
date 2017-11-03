package com.github.acschil.searching

import spock.lang.Specification
import spock.lang.Unroll

class BinarySearchSpec extends Specification {

    @Unroll
    def "finds element in a sorted list"() {
        when:
        int indexOfElement = BinarySearch.find(list, elementToFind)

        then:
        indexOfElement == expectedIndex

        where:
        list                                    | elementToFind || expectedIndex
        []                                      | 1             || -1
        [1]                                     | 1             || 0
        [0, 1, 2, 3, 4, 5]                      | 3             || 3
        [0, 1, 2, 3, 4, 5]                      | 2             || 2
        [0, 1, 2, 3, 4]                         | 2             || 2
        [-3, -1, 5, 9, 12, 23, 54, 78, 99, 101] | -3            || 0
        [-3, -1, 5, 9, 12, 23, 54, 78, 99, 101] | 101           || 9
        [-3, -1, 5, 9, 12, 23, 54, 78, 99, 101] | 58            || -1
        [-3, -1, 5, 9, 12, 23, 54, 78, 99, 101] | -5            || -1
        ["abc", "def", "ghi", "jkl", "mno"]     | "def"         || 1
    }
}
