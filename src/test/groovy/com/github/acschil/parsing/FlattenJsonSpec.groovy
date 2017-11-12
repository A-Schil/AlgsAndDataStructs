package com.github.acschil.parsing

import spock.lang.Specification
import spock.lang.Unroll

class FlattenJsonSpec extends Specification {
    def "flattens map"() {
        setup:
        Map toFlatten = [a: [b: 1, c: [d: 2, e: 3]], f: 4]

        when:
        FlattenJson.flattenSimpleMap(toFlatten)

        then:
        toFlatten == ['a.b': 1, 'a.c.d': 2, 'a.c.e': 3, 'f': 4]
    }
    
    def "flattenMap delegates to processList if lists are present"() {
        setup:
        GroovySpy(FlattenJson, global: true)

        when:
        FlattenJson.flattenMap([a: [b: [1, 2]]])

        then:
        1 * FlattenJson.processList([1, 2])
    }

    def "flattens map with lists"() {
        setup:
        Map toFlatten = [a: [b: [[d: [e: 1, f: 2], g: [3, 4]], [h: [[i: 5]]]]]]

        when:
        FlattenJson.flattenMap(toFlatten)

        then:
        toFlatten == ['a.b': [['g': [3, 4], 'd.e': 1, 'd.f': 2], ['h': [['i': 5]]]]]
    }

    @Unroll
    def "flattens json"() {
        expect:
        FlattenJson.flattenJsonAsMap(json) == expected

        where:
        json                                                           | expected
        '''{"a": {"b": [{"d": 2, "e": [4, 5]}, {"f": [{"g": 6}]}]}}''' | ["a.b": [["d": 2, "e": [4, 5]], ["f": [["g": 6]]]]]
        '''{"a": [{"b": {"c": 1, "d": 2}}, {"e": [4, 5]}]}'''          | ["a": [["b.c": 1, "b.d": 2], ["e": [4, 5]]]]
        '''{"a": [{"b": {"c": true, "d": "value"}}, {"e": [4, 5]}]}''' | ["a": [["b.c": true, "b.d": "value"], ["e": [4, 5]]]]
    }
}
