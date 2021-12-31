public class DirectedGraph private constructor(private val adjacencyMatrix: Map<Int, Array<Int>>) {
    companion object {
        public fun directedGraphCreator(adjacencyMatrix: Map<Int, Array<Int>>): DirectedGraph? =
            if (checkDirectedGraphIsValid(adjacencyMatrix)) DirectedGraph(adjacencyMatrix)
            else null

        private fun checkDirectedGraphIsValid(adjacencyMatrix: Map<Int, Array<Int>>): Boolean {
            if (adjacencyMatrix.isEmpty()) {
                return false
            }

            for (vertex in adjacencyMatrix.keys) {
                if (vertex < 0 || adjacencyMatrix[vertex]!!.isEmpty()) {
                    return false
                }

                for (adjacentVertex in adjacencyMatrix[vertex]!!) {
                    if (adjacentVertex < 0 || adjacencyMatrix[vertex]!!.count { vertex -> vertex == adjacentVertex } > 1) {
                        return false
                    }
                }
            }

            return true
        }
    }

    public fun reachableVertices(vertex: Int): Set<Int> {
        if (!adjacencyMatrix.containsKey(vertex)) {
            throw GraphDoesNotContainVertexException("Invalid vertex index!")
        }

        val queueToVisit = mutableListOf<Int>()
        val visitedVertices = mutableSetOf<Int>()

        queueToVisit.add(vertex)
        visitedVertices.add(vertex)

        while (queueToVisit.isNotEmpty()) {
            val currentVertex = queueToVisit.first()
            queueToVisit.removeFirst()

            if (adjacencyMatrix.contains(currentVertex)) {
                for (adjacentVertex in adjacencyMatrix[currentVertex]!!) {
                    if (!visitedVertices.contains(adjacentVertex)) {
                        queueToVisit.add(adjacentVertex)
                        visitedVertices.add(adjacentVertex)
                    }
                }
            }
        }

        return visitedVertices.toSet()
    }

    public override fun toString(): String {
        var graphRepresentationStr = "{"

        for (vertex in adjacencyMatrix.keys) {
            graphRepresentationStr += " { $vertex: "

            for (adjacentVertex in adjacencyMatrix[vertex]!!) {
                graphRepresentationStr += "$adjacentVertex"

                if (adjacencyMatrix[vertex]!!.last() != adjacentVertex) {
                    graphRepresentationStr += ", "
                }
            }

            graphRepresentationStr += " }"
        }

        graphRepresentationStr += " }"

        return  graphRepresentationStr
    }
}