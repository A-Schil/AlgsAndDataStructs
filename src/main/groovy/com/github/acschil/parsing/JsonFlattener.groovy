package com.github.acschil.parsing

import com.fasterxml.jackson.databind.ObjectMapper

class JsonFlattener {

    private final ObjectMapper mapper

    private static final JsonFlattener instance = new JsonFlattener()

    private JsonFlattener() {
        this.mapper = new ObjectMapper()
    }

    static JsonFlattener getInstance() {
        return instance
    }

    Map<String, Object> flattenJsonAsMap(String json) {
        Map<String, Object> map = mapper.readValue(json, Map)
        flattenMap(map)
        return map
    }

    /**
     * Uses a BFS to flatten a map of maps in place. Action is non-reversible
     *
     */
    private void flattenMap(Map<String, Object> map) {
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
    private processList(List list) {
        list.each {
            if (it instanceof Map) {
                flattenMap(it)
            } else if (it instanceof List) {
                processList(it)
            }
        }
    }

}
