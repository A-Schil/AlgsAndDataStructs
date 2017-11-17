package com.github.acschil.parsing

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
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

    /**
     *
     * Flattens JSON to a groovy map using a breadth-first traversal
     *
     * @param json string to flatten
     * @param flattenLists whether to flatten lists (e.g. {"a": [2, 4]} => ["a[0]": 2, "a[1]": 4]
     * @return the json as a Groovy Map<String, Object>
     */
    Map<String, Object> flattenJsonAsMap(String json, boolean flattenLists=false)
            throws IOException, JsonParseException, JsonMappingException {
        Map<String, Object> map = mapper.readValue(json, Map)
        flattenLists ? flattenMapAndLists(map) : flattenMap(map)
        return map
    }

    private void flattenMapAndLists(Map<String, Object> map) {
        Deque deque = new ArrayDeque(map.keySet().collect())
        while (deque.size() > 0) {
            String key = deque.pollLast()
            if (map[key] instanceof Map) {
                Map subValue = (Map) map.remove(key)
                subValue.keySet().each { subKey ->
                    String flattenedKey = "${key}.${subKey}"
                    map.put(flattenedKey, subValue[subKey])
                    deque.push(flattenedKey)
                }
            } else if (map[key] instanceof List) {
                List subValue = (List) map.remove(key)
                subValue.eachWithIndex { e, idx ->
                    String flattenedKey = "${key}[${idx}]"
                    map.put(flattenedKey, e)
                    deque.push(flattenedKey)
                }
            }
        }
    }

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
