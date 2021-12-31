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
                    if (adjacentVertex < 0 || adjacencyMatrix[vertex]!!.count() > 1) {
                        return false
                    }
                }
            }

            return true
        }
    }

    public override fun toString(): String {
        var graphRepresentationStr = ""

        for (vertex in adjacencyMatrix.keys) {
            graphRepresentationStr += "{ $vertex: "

            for (adjacentVertex in adjacencyMatrix[vertex]!!) {
                graphRepresentationStr += "$adjacentVertex"

                if (adjacencyMatrix[vertex]!!.last() != adjacentVertex) {
                    graphRepresentationStr += ", "
                }
            }

            graphRepresentationStr += " } "
        }

        return  graphRepresentationStr
    }
}