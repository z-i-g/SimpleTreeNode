import org.junit.jupiter.api.Test;

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
}