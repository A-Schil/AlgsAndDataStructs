package com.github.acschil.graphs.adjacencylist

import spock.lang.Specification

class AdjacencyListGraphSpec extends Specification {

    AdjacencyListGraph graph

    def setup() {
        graph = new AdjacencyListGraph()
    }

    def "graphFromList"() {
        setup:
        List<List<Integer>> expected = [[0, 1], [0, 2], [0]]

        when:
        AdjacencyListGraph newGraph = new AdjacencyListGraph(expected)

        then:
        newGraph.vertices == expected
    }

    def "graphFromList and getters"() {
        setup:
        List<List<Integer>> expected = [[0, 1], [0, 2], [0]]

        when:
        AdjacencyListGraph newGraph = new AdjacencyListGraph(expected)

        then:
        newGraph.vertices == expected
        newGraph.getNumEdges() == 5
        newGraph.getNumVertices() == 3
        newGraph.getVertexDegree(0) == 2
        newGraph.getVertexDegree(1) == 2
        newGraph.getVertexDegree(2) == 1
        newGraph.getGetConnectedVertices(0) == [0, 1]
        newGraph.getGetConnectedVertices(1) == [0, 2]
        newGraph.getGetConnectedVertices(2) == [0]
    }

    def "can add vertex to graph"() {
        expect:
        graph.addVertex() == 0
        graph.addVertex() == 1
    }

    def "can add vertex with outgoing connections to a list of neighbors"() {
        setup:
        graph.vertices = [[], [], []]

        when:
        int v = graph.addVertex([0, 1, 2])

        then:
        graph.vertices[v] == [0, 1, 2]

    }

    def "can add undirected edge from one vertex to another"() {
        setup:
        graph.vertices = [[], []]

        when:
        graph.addUndirectedEdge(0, 1)

        then:
        graph.vertices == [[1], [0]]
    }

    def "can add directed edge from one vertex to another"() {
        setup:
        graph.vertices = [[], []]

        when:
        graph.addDirectedEdge(0, 1)

        then:
        graph.vertices == [[1], []]
    }

    def "can get a vertex's outgoing connections"() {
        setup:
        graph.vertices = [[0, 1, 4], []]

        expect:
        graph.getGetConnectedVertices(0) == [0, 1, 4]
    }

    def "can get a vertex's degree"() {
        setup:
        graph.vertices = [[0, 1, 4], []]

        expect:
        graph.getVertexDegree(0) == 3
    }

    def "can remove "() {
        setup:
        graph.vertices = [[0, 1, 4], []]

        expect:
        graph.getVertexDegree(0) == 3
    }
}