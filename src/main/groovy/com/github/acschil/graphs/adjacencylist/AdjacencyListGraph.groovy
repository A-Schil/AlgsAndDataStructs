package com.github.acschil.graphs.adjacencylist

import com.github.acschil.graphs.exceptions.NoSuchVertexException

/**
 * Adjacency list implementation. Preferred implementation for sparse graphs i.e. |E| << |V|^2. This implementation
 * does not support weighted edges - use the adjacency matrix for problems that require weighted edges.
 */
class AdjacencyListGraph {
    private List<List<Integer>> vertices
    private int v
    private int e

    AdjacencyListGraph() {
        vertices = new ArrayList<List<Integer>>()
    }

    AdjacencyListGraph(List<List<Integer>> vertices) {
        this.vertices = new ArrayList<List<Integer>>(vertices.size() - 1)
        (0..(vertices.size() - 1)).each { int v ->
            this.vertices[v] = new LinkedList<>()
            this.v++
        }
        (0..(vertices.size() - 1)).each { int v ->
            (vertices[v]).each { int neighbor ->
                validateVertex(neighbor)
                this.vertices[v].add(neighbor)
                this.e++
            }
        }
    }

    int addVertex() {
        vertices.add(new LinkedList<Integer>())
        return vertices.size() - 1
    }

    int addVertex(List<Integer> connectedTo) {
        int vertex = vertices.size()
        LinkedList adjacencyList = new LinkedList()
        connectedTo.each {
            if (it != vertex) {
                validateVertex(it)
            }
            adjacencyList.add(it)
        }
        vertices.add(adjacencyList)
        return vertex
    }

    // TODO remove edge, vertex?

    void addUndirectedEdge(int v1, int v2) {
        validateVertex(v1)
        validateVertex(v2)
        vertices[v1].add(v2)
        vertices[v2].add(v1)
    }

    void addDirectedEdge(int from, int to) {
        validateVertex(from)
        validateVertex(to)
        vertices[from].add(to)
    }

    List<Integer> getGetConnectedVertices(int v) {
        validateVertex(v)
        return vertices[v].collect()
    }

    int getVertexDegree(int v) {
        validateVertex(v)
        return vertices[v].size()
    }

    int getNumVertices() {
        return v
    }

    int getNumEdges() {
        return e
    }

    String toString() {
        StringBuilder builder = new StringBuilder()
        (0..(vertices.size() - 1)).each {
            builder.append("Vertex ${it} connected to ${vertices[it]}")
        }
        return builder.toString()
    }

    private validateVertex(int v) {
        if (v < 0 || v >= vertices.size()) {
            throw new NoSuchVertexException("vertex ${v} does not exist in the graph")
        }
    }

}
