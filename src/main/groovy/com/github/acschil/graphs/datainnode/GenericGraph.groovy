package com.github.acschil.graphs.datainnode

class GenericGraph<K> {
    private Map<K, Vertex<K, ?>> vertices

    GenericGraph(Map vertices = new HashMap()) {
        this.vertices = vertices
    }

    void addVertex(Vertex<K, ?> vertex) {
        if(!vertices.containsKey(vertex.key)) {
            vertices.put(vertex.key, vertex)
        }
    }
}
