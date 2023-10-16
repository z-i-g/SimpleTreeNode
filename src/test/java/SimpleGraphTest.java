import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {
    SimpleGraph simpleGraph = new SimpleGraph(4);

    @Test
    public void addVertexWhenGraphIsEmpty() {
        simpleGraph.AddVertex(1);

        assertEquals(1, simpleGraph.vertex[0].Value);
        assertNull(simpleGraph.vertex[1]);
        assertNull(simpleGraph.vertex[2]);
    }

    @Test
    public void addVertexWhenGraphIsNotEmpty() {
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);

        assertEquals(1, simpleGraph.vertex[0].Value);
        assertEquals(2, simpleGraph.vertex[1].Value);
        assertNull(simpleGraph.vertex[2]);

        for (int i = 0; i < simpleGraph.vertex.length; i++) {
            assertEquals(0, simpleGraph.m_adjacency[2][i]);
        }
    }

    @Test
    public void addEdgeTest() {
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        assertEquals(0, simpleGraph.m_adjacency[0][1]);
        simpleGraph.AddEdge(0, 1);

        assertEquals(1, simpleGraph.vertex[0].Value);
        assertEquals(2, simpleGraph.vertex[1].Value);
        assertNull(simpleGraph.vertex[2]);
        assertEquals(1, simpleGraph.m_adjacency[0][1]);
        assertEquals(1, simpleGraph.m_adjacency[1][0]);
    }

    @Test
    public void removeEdgeTest() {
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddEdge(0, 1);
        assertEquals(1, simpleGraph.m_adjacency[0][1]);
        simpleGraph.RemoveEdge(0, 1);
        assertEquals(0, simpleGraph.m_adjacency[0][1]);
    }

    @Test
    public void removeVertexTest() {
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddEdge(0, 1);
        simpleGraph.AddEdge(0, 3);
        simpleGraph.AddEdge(1, 3);

        assertEquals(1, simpleGraph.m_adjacency[0][1]);
        assertEquals(1, simpleGraph.m_adjacency[0][3]);
        assertEquals(1, simpleGraph.m_adjacency[1][0]);
        assertEquals(1, simpleGraph.m_adjacency[3][0]);
        assertEquals(1, simpleGraph.m_adjacency[1][3]);
        assertEquals(1, simpleGraph.m_adjacency[3][1]);

        simpleGraph.RemoveVertex(0);

        assertEquals(0, simpleGraph.m_adjacency[0][1]);
        assertEquals(0, simpleGraph.m_adjacency[0][3]);
        assertEquals(0, simpleGraph.m_adjacency[1][0]);
        assertEquals(0, simpleGraph.m_adjacency[3][0]);
        assertEquals(1, simpleGraph.m_adjacency[1][3]);
        assertEquals(1, simpleGraph.m_adjacency[3][1]);
        assertNull(simpleGraph.vertex[0]);
    }

    @Test
    public void removeVertexTest2() {
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddEdge(0, 1);
        simpleGraph.AddEdge(0, 3);
        simpleGraph.AddEdge(2, 1);
        simpleGraph.AddEdge(3, 2);

        assertEquals(1, simpleGraph.m_adjacency[0][1]);
        assertEquals(1, simpleGraph.m_adjacency[0][3]);
        assertEquals(1, simpleGraph.m_adjacency[1][0]);
        assertEquals(1, simpleGraph.m_adjacency[1][2]);
        assertEquals(1, simpleGraph.m_adjacency[2][1]);
        assertEquals(1, simpleGraph.m_adjacency[2][3]);
        assertEquals(1, simpleGraph.m_adjacency[3][0]);
        assertEquals(1, simpleGraph.m_adjacency[3][2]);

        simpleGraph.RemoveVertex(0);

        assertEquals(0, simpleGraph.m_adjacency[0][0]);
        assertEquals(0, simpleGraph.m_adjacency[0][1]);
        assertEquals(0, simpleGraph.m_adjacency[0][2]);
        assertEquals(0, simpleGraph.m_adjacency[0][3]);
        assertEquals(0, simpleGraph.m_adjacency[1][0]);
        assertEquals(0, simpleGraph.m_adjacency[2][0]);
        assertEquals(0, simpleGraph.m_adjacency[3][0]);

        assertEquals(1, simpleGraph.m_adjacency[1][2]);
        assertEquals(1, simpleGraph.m_adjacency[1][2]);
        assertEquals(1, simpleGraph.m_adjacency[2][1]);
        assertEquals(1, simpleGraph.m_adjacency[2][3]);
        assertEquals(1, simpleGraph.m_adjacency[3][2]);

        assertNull(simpleGraph.vertex[0]);
    }

    @Test
    public void isEdgeTest() {
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddEdge(0, 1);
        simpleGraph.AddEdge(0, 3);
        simpleGraph.AddEdge(2, 1);
        simpleGraph.AddEdge(3, 2);

        assertTrue(simpleGraph.IsEdge(0, 1));
        assertTrue(simpleGraph.IsEdge(0, 3));
        assertTrue(simpleGraph.IsEdge(1, 0));
        assertTrue(simpleGraph.IsEdge(1, 2));
        assertTrue(simpleGraph.IsEdge(2, 1));
        assertTrue(simpleGraph.IsEdge(2, 3));
        assertTrue(simpleGraph.IsEdge(3, 0));
        assertTrue(simpleGraph.IsEdge(3, 2));

        simpleGraph.RemoveVertex(0);

        assertFalse(simpleGraph.IsEdge(0, 0));
        assertFalse(simpleGraph.IsEdge(0, 1));
        assertFalse(simpleGraph.IsEdge(0, 2));
        assertFalse(simpleGraph.IsEdge(0, 3));
        assertFalse(simpleGraph.IsEdge(1, 0));
        assertFalse(simpleGraph.IsEdge(2, 0));
        assertFalse(simpleGraph.IsEdge(3, 0));

        assertTrue(simpleGraph.IsEdge(1, 2));
        assertTrue(simpleGraph.IsEdge(2, 1));
        assertTrue(simpleGraph.IsEdge(2, 3));
        assertTrue(simpleGraph.IsEdge(3, 2));

        assertNull(simpleGraph.vertex[0]);
    }

    @Test
    public void depthFirstSearchTestWhenThereIsWay() {
        SimpleGraph simpleGraph = new SimpleGraph(11);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddVertex(9);
        simpleGraph.AddVertex(10);
        simpleGraph.AddEdge(0, 2);
        simpleGraph.AddEdge(2, 1);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(3, 4);
        simpleGraph.AddEdge(1, 4);
        simpleGraph.AddEdge(1, 5);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(1, 7);
        simpleGraph.AddEdge(7, 8);
        simpleGraph.AddEdge(8, 9);
        simpleGraph.AddEdge(9, 10);

        List<Integer> actualResult = simpleGraph.DepthFirstSearch(0, 10).stream()
                .map(vertex -> vertex.Value)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(0, 2, 1, 7, 8, 9, 10), actualResult);
    }

    @Test
    public void depthFirstSearchTestWhenThereIsNotWay() {
        SimpleGraph simpleGraph = new SimpleGraph(11);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddVertex(9);
        simpleGraph.AddVertex(10);
        simpleGraph.AddEdge(0, 2);
        simpleGraph.AddEdge(2, 1);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(3, 4);
        simpleGraph.AddEdge(1, 4);
        simpleGraph.AddEdge(1, 5);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(1, 7);
        simpleGraph.AddEdge(7, 8);
        simpleGraph.AddEdge(8, 9);

        List<Vertex> actualResult = simpleGraph.DepthFirstSearch(0, 10);

        assertEquals(Collections.EMPTY_LIST, actualResult);
    }

    @Test
    public void breadFirstSearchTestWhenThereIsWay() {
        SimpleGraph simpleGraph = new SimpleGraph(11);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddVertex(9);
        simpleGraph.AddVertex(10);
        simpleGraph.AddEdge(0, 2);
        simpleGraph.AddEdge(2, 1);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(3, 4);
        simpleGraph.AddEdge(1, 4);
        simpleGraph.AddEdge(1, 5);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(1, 7);
        simpleGraph.AddEdge(7, 8);
        simpleGraph.AddEdge(8, 9);
        simpleGraph.AddEdge(9, 10);

        List<Integer> actualResult = simpleGraph.BreadthFirstSearch(0, 10).stream()
                .map(vertex -> vertex.Value)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(0, 2, 1, 7, 8, 9, 10), actualResult);
    }

    @Test
    public void breadthFirstSearchTestWhenThereIsNotWay() {
        SimpleGraph simpleGraph = new SimpleGraph(11);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddVertex(9);
        simpleGraph.AddVertex(10);
        simpleGraph.AddEdge(0, 2);
        simpleGraph.AddEdge(2, 1);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(3, 4);
        simpleGraph.AddEdge(1, 4);
        simpleGraph.AddEdge(1, 5);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(1, 7);
        simpleGraph.AddEdge(7, 8);
        simpleGraph.AddEdge(8, 9);

        List<Vertex> actualResult = simpleGraph.DepthFirstSearch(0, 10);

        assertEquals(Collections.EMPTY_LIST, actualResult);
    }

    @Test
    public void breadFirstSearchTestWhenThereIsWayTwo() {
        SimpleGraph simpleGraph = new SimpleGraph(11);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddVertex(9);
        simpleGraph.AddVertex(10);
        simpleGraph.AddEdge(0, 2);
        simpleGraph.AddEdge(2, 1);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(3, 4);
        simpleGraph.AddEdge(1, 4);
        simpleGraph.AddEdge(1, 5);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(1, 7);
        simpleGraph.AddEdge(7, 8);
        simpleGraph.AddEdge(8, 9);
        simpleGraph.AddEdge(9, 10);
        simpleGraph.AddEdge(6, 10);


        List<Integer> actualResult = simpleGraph.BreadthFirstSearch(0, 10).stream()
                .map(vertex -> vertex.Value)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(0, 2, 1, 6, 10), actualResult);
    }

    @Test
    public void breadFirstSearchTestWhenThereIsWayThree() {
        SimpleGraph simpleGraph = new SimpleGraph(11);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddVertex(9);
        simpleGraph.AddVertex(10);
        simpleGraph.AddEdge(0, 2);
        simpleGraph.AddEdge(2, 1);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(3, 4);
        simpleGraph.AddEdge(1, 4);
        simpleGraph.AddEdge(1, 5);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(1, 7);
        simpleGraph.AddEdge(7, 8);
        simpleGraph.AddEdge(8, 9);
        simpleGraph.AddEdge(9, 10);
        simpleGraph.AddEdge(6, 10);


        List<Integer> actualResult = simpleGraph.BreadthFirstSearch(0, 4).stream()
                .map(vertex -> vertex.Value)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(0, 2, 1, 4), actualResult);
    }

    @Test
    public void weakVerticesTestWhenContainsWithOutTriangleVertex() {
        SimpleGraph simpleGraph = new SimpleGraph(9);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddEdge(0, 1);
        simpleGraph.AddEdge(0, 2);
        simpleGraph.AddEdge(0, 3);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(2, 5);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(3, 5);
        simpleGraph.AddEdge(3, 6);
        simpleGraph.AddEdge(6, 4);
        simpleGraph.AddEdge(6, 7);
        simpleGraph.AddEdge(4, 7);
        simpleGraph.AddEdge(7, 8);

        List<Integer> actualResult = simpleGraph.WeakVertices().stream()
                .map(vertex -> vertex.Value)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(1, 8), actualResult);
    }

    @Test
    public void weakVerticesTestWhenNotContainsWithOutTriangleVertex() {
        SimpleGraph simpleGraph = new SimpleGraph(9);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddEdge(0, 1);
        simpleGraph.AddEdge(0, 2);
        simpleGraph.AddEdge(0, 3);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(2, 5);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(3, 1);
        simpleGraph.AddEdge(3, 5);
        simpleGraph.AddEdge(3, 6);
        simpleGraph.AddEdge(6, 4);
        simpleGraph.AddEdge(6, 7);
        simpleGraph.AddEdge(6, 8);
        simpleGraph.AddEdge(4, 7);
        simpleGraph.AddEdge(7, 8);

        List<Integer> actualResult = simpleGraph.WeakVertices().stream()
                .map(vertex -> vertex.Value)
                .collect(Collectors.toList());

        assertEquals(Collections.emptyList(), actualResult);
    }

    @Test
    public void weakVerticesTestWhenAllVertexContainsWithOutTriangle() {
        SimpleGraph simpleGraph = new SimpleGraph(9);
        simpleGraph.AddVertex(0);
        simpleGraph.AddVertex(1);
        simpleGraph.AddVertex(2);
        simpleGraph.AddVertex(3);
        simpleGraph.AddVertex(4);
        simpleGraph.AddVertex(5);
        simpleGraph.AddVertex(6);
        simpleGraph.AddVertex(7);
        simpleGraph.AddVertex(8);
        simpleGraph.AddEdge(0, 1);
        simpleGraph.AddEdge(0, 3);
        simpleGraph.AddEdge(2, 3);
        simpleGraph.AddEdge(1, 6);
        simpleGraph.AddEdge(3, 5);
        simpleGraph.AddEdge(3, 6);
        simpleGraph.AddEdge(6, 7);
        simpleGraph.AddEdge(4, 7);
        simpleGraph.AddEdge(7, 8);

        List<Integer> actualResult = simpleGraph.WeakVertices().stream()
                .map(vertex -> vertex.Value)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8), actualResult);
    }

    @Test
    public void weakVerticesTestWhenEmptyGraph() {
        SimpleGraph simpleGraph = new SimpleGraph(0);

        List<Integer> actualResult = simpleGraph.WeakVertices().stream()
                .map(vertex -> vertex.Value)
                .collect(Collectors.toList());

        assertEquals(Collections.emptyList(), actualResult);
    }
}