package com.technosudo

import com.technosudo.structures.Node
import kotlin.math.max
import kotlin.random.Random

object GraphFactory {

    fun createSimpleGraph(
        size: Int,
        aveNumConn: Double,
        minNumConn: Int = 1
    ): Set<Node> {
        // for now it just ands it just adds minNumConn num of edges to every node
        val aveNumConnMore = max(minNumConn.toDouble(), aveNumConn) - minNumConn
        val graph = mutableSetOf<Node>()
        for (i in 0..<size) {
            graph.add(Node(i, Random.nextDouble(10.0), Random.nextDouble(10.0), mutableSetOf()))
        }
        val connected = mutableSetOf<Node>()
        for (node in graph) {
            val toConnect = mutableSetOf<Node>()
            repeat(minNumConn) {
                connected
                    .filter { !toConnect.contains(it) }
                    .randomOrNull()
                    ?.let { toConnect.add(it) }
            }
            for (connectedNode in toConnect) {
                val distance = node.distanceStraight(connectedNode)
                connectedNode.connections.add(Pair(node, distance))
                node.connections.add(Pair(connectedNode, distance))
            }
            connected.add(node)
        }
        return graph
    }
}