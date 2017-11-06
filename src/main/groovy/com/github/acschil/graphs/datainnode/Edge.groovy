package com.github.acschil.graphs.datainnode

class Edge<T> {
    Vertex start
    Vertex end
    double weight
    T data

    Edge(Vertex start, Vertex end, double weight) {
        this(start, end, weight, null)
    }


    Edge(Vertex start, Vertex end, double weight, T data) {
        this.start = start
        this.end = end
        this.weight = weight
        this.data = data
    }
}
