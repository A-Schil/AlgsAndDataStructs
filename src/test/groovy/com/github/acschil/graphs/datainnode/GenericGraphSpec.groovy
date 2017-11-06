package com.github.acschil.graphs.datainnode

import spock.lang.Specification

class GenericGraphSpec extends Specification {

    def "can add vertices to the directed, unweighted graph"() {
        setup:
        GenericGraph<Integer> graph = new GenericGraph<>()

        Vertex<Integer, String> vertex1 = new Vertex<>(1, "one")
        Vertex<Integer, Double> vertex2 = new Vertex<>(2, 2.2)
        Vertex<Integer, Character> vertex3 = new Vertex<>(3, 't' as char)

        vertex1.adjacentVertices.add(vertex2)
        vertex2.adjacentVertices.add(vertex1)
        vertex2.adjacentVertices.add(vertex3)
        vertex3.adjacentVertices.add(vertex2)

        when:
        /*
         *     1 <--> 2
         *            ^
         *            |
         *            3
         */
        graph.addVertex(vertex1)
        graph.addVertex(vertex2)
        graph.addVertex(vertex3)

        then:
        vertex1.key == 1
        vertex1.data == "one"
        vertex1.adjacentVertices == [vertex2]

        vertex2.key == 2
        vertex2.data == 2.2
        vertex2.adjacentVertices == [vertex1, vertex3]

        vertex3.key == 3
        vertex3.data == 't'
        vertex3.adjacentVertices == [vertex2]
    }

    def "can add vertices to the directed, weighted graph"() {
        setup:
        GenericGraph<Integer> graph = new GenericGraph<>()

        Vertex<Integer, String> vertex1 = new Vertex<>(1, "one")
        Vertex<Integer, Double> vertex2 = new Vertex<>(2, 2.2)
        Vertex<Integer, Character> vertex3 = new Vertex<>(3, 't' as char)

        Edge edgeV1V2 = new Edge(vertex1, vertex2, 2)
        Edge edgeV2V1 = new Edge(vertex2, vertex1, -2)
        Edge edgeV3V2 = new Edge(vertex3, vertex2, 5)

        vertex1.adjacentVertices.add(vertex2)
        vertex2.adjacentVertices.add(vertex1)
        vertex2.adjacentVertices.add(vertex3)
        vertex3.adjacentVertices.add(vertex2)

        vertex1.edges.add(edgeV1V2)
        vertex2.edges.add(edgeV2V1)
        vertex3.edges.add(edgeV3V2)



        when:
        /*          2
         *     1 <---> 2
         *       -2    ^
         *             | 5
         *             3
         */
        graph.addVertex(vertex1)
        graph.addVertex(vertex2)
        graph.addVertex(vertex3)

        then:
        vertex1.key == 1
        vertex1.data == "one"
        vertex1.adjacentVertices == [vertex2]
        vertex1.edges == [edgeV1V2]

        vertex2.key == 2
        vertex2.data == 2.2
        vertex2.adjacentVertices == [vertex1, vertex3]
        vertex2.edges == [edgeV2V1]

        vertex3.key == 3
        vertex3.data == 't'
        vertex3.adjacentVertices == [vertex2]
        vertex3.edges == [edgeV3V2]
    }
}
