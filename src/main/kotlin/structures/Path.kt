package com.technosudo.structures

data class Path constructor(
    val nodes: List<Node>,
    val time: Double
) {
    override fun toString(): String {
        val time = String.format("%.2f", this.time)
        val nodes = this.nodes.map { it.id }
        return "Path(time=$time, nodes=$nodes)"
    }
}