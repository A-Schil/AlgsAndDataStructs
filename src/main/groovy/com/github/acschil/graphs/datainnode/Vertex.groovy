package com.github.acschil.graphs.datainnode

class Vertex<K, T> {
    K key
    T data
    List<Vertex> adjacentVertices
    List<Edge> edges

    Vertex(K key, T data, List<Vertex> adjacentVertices=[], List<Edge> edges=[]) {
        this.key = key
        this.data = data
        this.adjacentVertices = adjacentVertices
        this.edges = edges
    }

}
