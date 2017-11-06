package com.github.acschil.graphs.adjacencymatrix

import com.github.acschil.graphs.exceptions.NoSuchVertexException

/**
 * Adjacency Matrix implementation. Preferred implementation for dense graphs i.e. |E| is close to |V|^2, or
 * when we need to quickly know if there is an edge connecting two given vertices. Implemented to support weights.
 * An edge exists between two vertex a to b iff vertices[a][b] < Integer.MIN_VALUE.
 */
class AdjacencyMatrixGraph {
    private final int v
    private final int e
    private final Integer[][] adjacencyMatrix

    AdjacencyMatrixGraph(Integer[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix
        this.v = adjacencyMatrix.length
        int e = 0
        (0..(adjacencyMatrix.length - 1)).each { int row ->
            (0..(adjacencyMatrix.length - 1)).each { int col ->
                if (adjacencyMatrix[row][col] != null) {
                    e++
                }
            }
        }
        this.e = e
    }

    List<Integer> getConnectedVertices(int v) {
        validateVertex(v)
        List<Integer> neighbors = []
        (0..(adjacencyMatrix.length - 1)).each { int col ->
            if (adjacencyMatrix[v][col] != null) {
                neighbors.add(col)
            }
        }
        return neighbors
    }

    Integer getEdgeWeight(int from, int to) {
        return adjacencyMatrix[from][to]
    }

    boolean isConnected(int from, int to) {
        return adjacencyMatrix[from][to] != null
    }

    int getVertexDegree(int v) {
        validateVertex(v)
        int degree = 0
        (0..(adjacencyMatrix.length - 1)).each { int col ->
            if (adjacencyMatrix[v][col] != null) {
                degree++
            }
        }
        return degree
    }

    int getNumVertices() {
        return v
    }

    int getNumEdges() {
        return e
    }

    String toString() {
        StringBuilder builder = new StringBuilder()
        (0..(adjacencyMatrix.length - 1)).each { int row ->
            List<Integer> neighbors = []
            (0..(adjacencyMatrix.length - 1)).each { int col ->
                if (adjacencyMatrix[row][col] != null) {
                    neighbors.add(adjacencyMatrix[row][col])
                }
            }
            builder.append("Vertex ${row} connected to ${neighbors}")
        }
        return builder.toString()
    }

    private validateVertex(int v) {
        if (v < 0 || v >= adjacencyMatrix.length) {
            throw new NoSuchVertexException("vertex ${v} does not exist in the graph")
        }
    }
}
