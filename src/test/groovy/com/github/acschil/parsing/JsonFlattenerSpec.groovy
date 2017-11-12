package com.github.acschil.parsing

import spock.lang.Specification
import spock.lang.Unroll

class JsonFlattenerSpec extends Specification {

    JsonFlattener jsonFlattener = JsonFlattener.instance

    def "flattens map with lists"() {
        setup:
        Map toFlatten = [a: [b: [[d: [e: 1, f: 2], g: [3, 4]], [h: [[i: 5]]]]]]

        when:
        jsonFlattener.flattenMap(toFlatten)

        then:
        toFlatten == ['a.b': [['g': [3, 4], 'd.e': 1, 'd.f': 2], ['h': [['i': 5]]]]]
    }

    @Unroll
    def "flattens json"() {
        expect:
        jsonFlattener.flattenJsonAsMap(json) == expected

        where:
        json                                                           | expected
        '''{"a": {"b": [{"d": 2, "e": [4, 5]}, {"f": [{"g": 6}]}]}}''' | ["a.b": [["d": 2, "e": [4, 5]], ["f": [["g": 6]]]]]
        '''{"a": [{"b": {"c": 1, "d": 2}}, {"e": [4, 5]}]}'''          | ["a": [["b.c": 1, "b.d": 2], ["e": [4, 5]]]]
        '''{"a": [{"b": {"c": true, "d": "value"}}, {"e": [4, 5]}]}''' | ["a": [["b.c": true, "b.d": "value"], ["e": [4, 5]]]]
    }
}
