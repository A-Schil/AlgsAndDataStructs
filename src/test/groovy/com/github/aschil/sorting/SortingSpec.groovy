package com.github.aschil.sorting

import spock.lang.Specification
import spock.lang.Unroll

class SortingSpec extends Specification {
    @Unroll
    def "sorts listToSort: #listToSort into expected: #expected of numbers using #sortingStrategy"() {
        when:
        sortingStrategy.sort(listToSort)

        then:
        listToSort == expected

        where:
        listToSort                                     | sortingStrategy || expected

        []                                             | Quicksort       || []
        [1, 2, 3]                                      | Quicksort       || [1, 2, 3]
        [1, 5, 6, 9, 2]                                | Quicksort       || [1, 2, 5, 6, 9]
        [5]                                            | Quicksort       || [5]
        [1, 2, 2, 2, 2, 3]                             | Quicksort       || [1, 2, 2, 2, 2, 3]
        [2.0, 4.8, 3.995, 3.99999, 1, 9.2357, 3.14159] | Quicksort       || [1, 2.0, 3.14159, 3.995, 3.99999, 4.8, 9.2357]
        [1, -1, 0]                                     | Quicksort       || [-1, 0, 1]
        ["A", "C", "D", "B"]                           | Quicksort       || ["A", "B", "C", "D"]
        ["A", "B", "C"]                                | Quicksort       || ["A", "B", "C"]
        ["B", "a", "A", "b"]                           | Quicksort       || ["A", "B", "a", "b"]
        ["dog", "cat", "doggo"]                        | Quicksort       || ["cat", "dog", "doggo"]

        []                                             | MergeSort       || []
        [1, 2, 3]                                      | MergeSort       || [1, 2, 3]
        [1, 5, 6, 9, 2]                                | MergeSort       || [1, 2, 5, 6, 9]
        [5]                                            | MergeSort       || [5]
        [1, 2, 2, 2, 2, 3]                             | MergeSort       || [1, 2, 2, 2, 2, 3]
        [2.0, 4.8, 3.995, 3.99999, 1, 9.2357, 3.14159] | MergeSort       || [1, 2.0, 3.14159, 3.995, 3.99999, 4.8, 9.2357]
        [1, -1, 0]                                     | MergeSort       || [-1, 0, 1]
        ["A", "C", "D", "B"]                           | MergeSort       || ["A", "B", "C", "D"]
        ["A", "B", "C"]                                | MergeSort       || ["A", "B", "C"]
        ["B", "a", "A", "b"]                           | MergeSort       || ["A", "B", "a", "b"]
        ["dog", "cat", "doggo"]                        | MergeSort       || ["cat", "dog", "doggo"]

        []                                             | HeapSort       || []
        [1, 2, 3]                                      | HeapSort       || [1, 2, 3]
        [1, 5, 6, 9, 2]                                | HeapSort       || [1, 2, 5, 6, 9]
        [5]                                            | HeapSort       || [5]
        [1, 2, 2, 2, 2, 3]                             | HeapSort       || [1, 2, 2, 2, 2, 3]
        [2.0, 4.8, 3.995, 3.99999, 1, 9.2357, 3.14159] | HeapSort       || [1, 2.0, 3.14159, 3.995, 3.99999, 4.8, 9.2357]
        [1, -1, 0]                                     | HeapSort       || [-1, 0, 1]
        ["A", "C", "D", "B"]                           | HeapSort       || ["A", "B", "C", "D"]
        ["A", "B", "C"]                                | HeapSort       || ["A", "B", "C"]
        ["B", "a", "A", "b"]                           | HeapSort       || ["A", "B", "a", "b"]
        ["dog", "cat", "doggo"]                        | HeapSort       || ["cat", "dog", "doggo"]
    }
}
