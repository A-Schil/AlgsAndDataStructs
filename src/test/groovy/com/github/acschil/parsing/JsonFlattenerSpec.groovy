package com.github.acschil.parsing

import com.fasterxml.jackson.databind.ObjectMapper
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
    def "flattens json, preserves lists"() {
        expect:
        jsonFlattener.flattenJsonAsMap(json, true) == expected

        where:
        json                                                           | expected
        '''{"a": {"b": [{"d": 2, "e": [4, 5]}, {"f": [{"g": 6}]}]}}''' | ["a.b": [["d": 2, "e": [4, 5]], ["f": [["g": 6]]]]]
        '''{"a": [{"b": {"c": 1, "d": 2}}, {"e": [4, 5]}]}'''          | ["a": [["b.c": 1, "b.d": 2], ["e": [4, 5]]]]
        '''{"a": [{"b": {"c": true, "d": "value"}}, {"e": [4, 5]}]}''' | ["a": [["b.c": true, "b.d": "value"], ["e": [4, 5]]]]
    }

    def "flattens json"() {
        String json = '''{"a": {"b": [{"d": 2, "e": [4, 5]}, {"f": [{"g": 6}]}]}}'''
        Map mapBeforeFlattening = (new ObjectMapper()).readValue(json, Map)

        when:
        Map result = jsonFlattener.flattenJsonAsMap('''{"a": {"b": [{"d": 2, "e": [4, 5]}, {"f": [{"g": 6}]}]}}''')

        then:
        result == ["a.b[0].d": 2, "a.b[0].e[0]": 4, "a.b[0].e[1]": 5, "a.b[1].f[0].g": 6]
        mapBeforeFlattening.a.b[0].d == result["a.b[0].d"]
        mapBeforeFlattening.a.b[0].e[0] == result["a.b[0].e[0]"]
        mapBeforeFlattening.a.b[0].e[1] == result["a.b[0].e[1]"]
        mapBeforeFlattening.a.b[1].f[0].g == result["a.b[1].f[0].g"]
    }
}
