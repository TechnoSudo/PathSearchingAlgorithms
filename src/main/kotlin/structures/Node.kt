package com.technosudo.structures

import kotlin.math.pow
import kotlin.math.sqrt

data class Node(
    val id: Int,
    val x: Double,
    val y: Double,
    val connections: MutableSet<Pair<Node, Double>> = mutableSetOf()
) {
    fun distanceStraight(other: Node): Double {
        return sqrt((x - other.x).pow(2) + (y - other.y).pow(2))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false
        return x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        return 31 * x.hashCode() + y.hashCode()
    }

    override fun toString(): String {
        val x = String.format("%.2f", this.x)
        val y = String.format("%.2f", this.y)
        val connections = this.connections.map { it.first.id }
        return "Node($id x=$x, y=$y, connections=$connections)"
    }
}
