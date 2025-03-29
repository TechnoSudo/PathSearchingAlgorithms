package com.technosudo

import com.technosudo.Misc.ave
import com.technosudo.Misc.significant
import com.technosudo.Misc.std
import kotlin.time.Duration
import kotlin.time.measureTime

object Simulation {

    fun run() {

        for (i in listOf(10, 100, 1000, 5000)) {
            val graph = GraphFactory.createSimpleGraph(size = i, aveNumConn = 2.0)
            val dijkstraResults = mutableListOf<Duration>()
            val bellmanFordResults = mutableListOf<Duration>()

            repeat(30) {
                val start = graph.random()
                val end =  graph.filterNot { it == start }.random()

                dijkstraResults.add(measureTime{ Algorithms.dijkstra(start, end)!! })
                bellmanFordResults.add(measureTime{ Algorithms.bellmanFord(graph, start, end) })
            }

            println("Repetitions: $i")
            println("Dijkstra:")
            printResults(dijkstraResults)
            println("Bellman-Ford:")
            printResults(bellmanFordResults)
        }

        return
    }

    fun printResults(results: Collection<Duration>) {
        println("Min: ${results.min().significant()}")
        println("Max: ${results.max().significant()}")
        println("Ave: ${results.ave().significant()}")
        println("Std: ${results.std().significant()}")
    }
}