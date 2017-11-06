package com.github.acschil.graphs.adjacencymatrix

import spock.lang.Specification

class AdjacencyMatrixGraphSpec extends Specification {

    AdjacencyMatrixGraph graph

    void setup() {
        Integer[][] newGraph = [
                [null, 0, 1],
                [2, null, 2],
                [-2, null, null]]
        graph = new AdjacencyMatrixGraph(newGraph)
    }

    def "GetGetConnectedVertices"() {
        expect:
        graph.getConnectedVertices(0) == [1, 2]
        graph.getConnectedVertices(1) == [0, 2]
        graph.getConnectedVertices(2) == [0]
    }

    def "GetEdgeWeight"() {
        expect:
        graph.getEdgeWeight(0, 2) == 1
        graph.getEdgeWeight(1, 1) == null
    }

    def "IsConnected"() {
        expect:
        !graph.isConnected(2, 1)
        graph.isConnected(0, 1)
    }

    def "GetVertexDegree"() {
        expect:
        graph.getVertexDegree(0) == 2
        graph.getVertexDegree(1) == 2
        graph.getVertexDegree(2) == 1
    }

    def "GetNumVertices"() {
        expect:
        graph.getNumVertices() == 3
    }

    def "GetNumEdges"() {
        expect:
        graph.getNumEdges() == 5
    }
}
