package com.technosudo

import com.technosudo.structures.Node
import com.technosudo.structures.Path
import java.util.PriorityQueue

object Algorithms {

    fun dijkstra(start: Node, end: Node): Path? {

        val possiblePaths = PriorityQueue<Path>(compareBy { it.time })
        val enteredNodes: MutableSet<Node> = mutableSetOf()
        possiblePaths.add(
            Path(
            nodes = listOf(start),
            time = 0.0
        )
        )
        while (possiblePaths.isNotEmpty()) {
            val path = possiblePaths.poll() ?: return null
            val node = path.nodes.last()
            if (node == end)
                return path
            enteredNodes.add(node)
            possiblePaths.addAll(node.connections
                .filterNot { nextNode -> enteredNodes.contains(nextNode.first) }
                .map { nextNode -> Path(path.nodes + nextNode.first, path.time + nextNode.second) }
            )
        }
        return null
    }

    fun bellmanFord(graph: Collection<Node>, start: Node, end: Node): Path {

        val states = graph
            .associateWith { Path(nodes = emptyList(), Double.POSITIVE_INFINITY) }
            .toMutableMap()
        states[start] = Path(nodes = listOf(start), 0.0)
        if (start == end)
            return states[start]!!

        repeat(states.size) {
            var updated = false
            for (state in states.filterValues { it.time != Double.POSITIVE_INFINITY }) {
                for (connection in state.key.connections) {
                    val newTime = state.value.time + connection.second
                    if (states[connection.first]!!.time > newTime) {
                        updated = true
                        states[connection.first] = Path(nodes = state.value.nodes + connection.first, time = newTime)
                    }
                }
            }
            if (!updated)
                return@repeat
        }
        return states[end]!!
    }
}