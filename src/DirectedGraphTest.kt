import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test

internal class DirectedGraphTest {
    @TestFactory
    fun reachableVertices() = reachableVerticesTestData.map { (expected, input) ->
        DynamicTest.dynamicTest("Test expected: $expected") {
            Assertions.assertEquals(expected, DirectedGraph.directedGraphCreator(input.first)!!.reachableVertices(input.second))
        }
    }

    @TestFactory
    fun testToString() = toStringTestData.map { (expected, input) ->
        DynamicTest.dynamicTest("Test expected: $expected") {
            Assertions.assertEquals(expected, DirectedGraph.directedGraphCreator(input).toString())
        }
    }

    @Test
    fun directedGraphCreatorReturnsNotNull() {
        Assertions.assertNotNull(DirectedGraph.directedGraphCreator(mutableMapOf(1 to arrayOf(2, 3))))
    }

    @Test
    fun directedGraphCreatorReturnsNull() {
        Assertions.assertNull(DirectedGraph.directedGraphCreator(mutableMapOf(1 to arrayOf(2, 2, 3))))
    }

    private val reachableVerticesTestData = listOf(
        setOf(1, 2, 3) to Pair(mapOf(1 to arrayOf(2, 3)), 1),
        setOf(1, 2, 3) to Pair(mapOf(1 to arrayOf(2, 3, 1)), 1),
        setOf(1, 2, 3, 4) to Pair(mapOf(1 to arrayOf(2), 2 to arrayOf(3), 3 to arrayOf(4), 4 to arrayOf(1), 5 to arrayOf(3)), 1),
        setOf(1, 2, 3, 4 , 5) to Pair(mapOf(1 to arrayOf(2), 2 to arrayOf(3), 3 to arrayOf(4, 5), 4 to arrayOf(1)), 1)
    )

    private val toStringTestData = listOf(
        "{ { 1: 2, 3 } }" to mapOf(1 to arrayOf(2, 3)),
        "{ { 1: 2, 3, 1 } }" to mapOf(1 to arrayOf(2, 3, 1)),
        "{ { 1: 2 } { 2: 3 } { 3: 4 } { 4: 1 } { 5: 3 } }" to mapOf(1 to arrayOf(2), 2 to arrayOf(3), 3 to arrayOf(4), 4 to arrayOf(1), 5 to arrayOf(3)),
        "{ { 1: 2 } { 2: 3 } { 3: 4, 5 } { 4: 1 } }" to mapOf(1 to arrayOf(2), 2 to arrayOf(3), 3 to arrayOf(4, 5), 4 to arrayOf(1))
    )
}