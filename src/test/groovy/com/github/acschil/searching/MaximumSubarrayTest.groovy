package com.github.acschil.searching

import spock.lang.Specification

class MaximumSubarrayTest extends Specification {
    def "Kadanes"() {
        setup:
        int[] array = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

        expect:
        MaximumSubarray.kadanes(array) == 6
    }
}
