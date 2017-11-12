package com.github.acschil.parsing

import com.fasterxml.jackson.databind.ObjectMapper

class FlattenJson {

    static Map<String, Object> flattenJsonAsMap(String json) {
        ObjectMapper mapper = new ObjectMapper()
        Map<String, Object> map = mapper.readValue(json, Map)
        flattenMap(map)
        return map
    }

    /**
     * Uses a BFS to flatten a map. All values are Maps, Strings, or primitives.
     *
     */
    private static void flattenSimpleMap(Map<String, Object> map) {
        Deque deque = new ArrayDeque(map.keySet().collect())
        while (deque.size() > 0) {
            String key = deque.pollLast()
            if (map[key] instanceof Map) {
                Map subValue = (Map) map.remove(key)
                subValue.keySet().collect().each { subKey ->
                    String flattenedKey = "${key}.${subKey}"
                    map.put(flattenedKey, subValue[subKey])
                    deque.push(flattenedKey)
                }
            }
        }
    }

    /**
     * Uses a BFS to flatten a map of maps in place. Action is non-reversible
     *
     */
    private static void flattenMap(Map<String, Object> map) {
        Deque deque = new ArrayDeque(map.keySet().collect())
        while (deque.size() > 0) {
            String key = deque.pollLast()
            if (map[key] instanceof Map) {
                Map subValue = (Map) map.remove(key)
                subValue.keySet().collect().each { subKey ->
                    String flattenedKey = "${key}.${subKey}"
                    map.put(flattenedKey, subValue[subKey])
                    deque.push(flattenedKey)
                }
            } else if (map[key] instanceof List) {
                processList((List) map[key])
            }
        }
    }

    /**
     * Recursively handles lists to flatten
     *
     */
    private static processList(List list) {
        list.each {
            if (it instanceof Map) {
                flattenMap(it)
            } else if (it instanceof List) {
                processList(it)
            }
        }
    }

}
