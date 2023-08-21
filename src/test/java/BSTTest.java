import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    private final BSTNode<Integer> rootNode = new BSTNode<>(4, 44, null);
    private final BST<Integer> bst = new BST<>(rootNode);

    @Test
    public void findNodeByKeyWhenKeyContainsAndOneNode() {
        BSTFind<Integer> bstFind = bst.FindNodeByKey(4);

        assertEquals(4, bstFind.Node.NodeKey);
        assertEquals(44, bstFind.Node.NodeValue);
        assertTrue(bstFind.NodeHasKey);
    }

    @Test
    public void findNodeByKeyWhenKeyContainsAndSomeNode() {
        bst.AddKeyValue(4, 44);
        bst.AddKeyValue(2, 22);
        BSTFind<Integer> bstFind = bst.FindNodeByKey(2);

        assertEquals(22, bstFind.Node.NodeValue);
        assertEquals(2, bstFind.Node.NodeKey);
        assertTrue(bstFind.NodeHasKey);
    }

    @Test
    public void addKeyValueWhenKeyContainsAndOnlyRootNode() {
        assertFalse(bst.AddKeyValue(4, 44));
        assertNull(rootNode.LeftChild);
        assertNull(rootNode.RightChild);
    }

    @Test
    public void addKeyValueWhenKeyContainsAndSomeNode() {
        bst.AddKeyValue(5, 55);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        assertFalse(bst.AddKeyValue(5, 55));

        assertEquals(1, rootNode.LeftChild.NodeKey);
        assertEquals(2, rootNode.LeftChild.RightChild.NodeKey);
        assertEquals(5, rootNode.RightChild.NodeKey);
        assertNotNull(rootNode.RightChild);
        assertNull(rootNode.RightChild.LeftChild);
        assertNull(rootNode.RightChild.RightChild);
    }

    @Test
    public void addKeyValueWhenKeyNotContainsAndAddLeftChild() {
        BSTFind<Integer> bstFind = bst.FindNodeByKey(2);

        assertFalse(bstFind.NodeHasKey);
        assertTrue(bst.AddKeyValue(5, 55));

        bstFind = bst.FindNodeByKey(5);

        assertEquals(5, bstFind.Node.NodeKey);
        assertEquals(rootNode, bstFind.Node.Parent);
        assertEquals(rootNode.RightChild, bstFind.Node);
    }

    @Test
    public void addKeyValueWhenKeyNotContainsAndAddRightChild() {
        BSTFind<Integer> bstFind = bst.FindNodeByKey(3);

        assertFalse(bstFind.NodeHasKey);
        assertTrue(bst.AddKeyValue(3, 33));

        bstFind = bst.FindNodeByKey(3);

        assertEquals(3, bstFind.Node.NodeKey);
        assertEquals(rootNode, bstFind.Node.Parent);
        assertEquals(rootNode.LeftChild, bstFind.Node);
    }

    @Test
    public void findMinWhenFromNodeIsRoot() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(6, 66);
        bst.AddKeyValue(5, 55);

        BSTNode<Integer> minBstNode = bst.FinMinMax(rootNode, false);

        assertEquals(1, minBstNode.NodeKey);
    }

    @Test
    public void findMinWhenFromNodeIsNotRoot() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(6, 66);
        bst.AddKeyValue(5, 55);

        BSTNode<Integer> fundNode = bst.FindNodeByKey(3).Node;
        BSTNode<Integer> minBstNode = bst.FinMinMax(fundNode, false);

        assertEquals(1, minBstNode.NodeKey);
    }

    @Test
    public void findMaxWhenFromNodeIsRoot() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(6, 66);
        bst.AddKeyValue(5, 55);

        BSTNode<Integer> minBstNode = bst.FinMinMax(rootNode, true);

        assertEquals(6, minBstNode.NodeKey);
    }

    @Test
    public void findMaxWhenFromNodeIsNotRoot() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(6, 66);
        bst.AddKeyValue(5, 55);
        bst.AddKeyValue(7, 77);
        bst.AddKeyValue(10, 100);
        bst.AddKeyValue(9, 99);

        BSTNode<Integer> fundNode = bst.FindNodeByKey(6).Node;
        BSTNode<Integer> minBstNode = bst.FinMinMax(fundNode, true);

        assertEquals(10, minBstNode.NodeKey);
    }

    @Test
    public void deleteWhenNodeHasNoChildNodes() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);

        assertEquals(3, bst.Count());
        assertTrue(bst.DeleteNodeByKey(1));
        assertEquals(3, rootNode.LeftChild.NodeKey);
        assertNull(rootNode.LeftChild.LeftChild);
        assertNull(rootNode.LeftChild.RightChild);
        assertEquals(2, bst.Count());
    }

    @Test
    public void deleteWhenNodeHasOnlyLeftNode() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);

        assertEquals(3, bst.Count());
        assertTrue(bst.FindNodeByKey(3).NodeHasKey);
        assertTrue(bst.DeleteNodeByKey(3));
        assertFalse(bst.FindNodeByKey(3).NodeHasKey);
        assertEquals(1, rootNode.LeftChild.NodeKey);
        assertNull(rootNode.LeftChild.LeftChild);
        assertNull(rootNode.LeftChild.RightChild);
        assertEquals(2, bst.Count());
    }

    @Test
    public void deleteWhenNodeHasOnlyRightNode() {
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(3, 33);

        assertEquals(3, bst.Count());
        assertTrue(bst.FindNodeByKey(3).NodeHasKey);
        assertTrue(bst.DeleteNodeByKey(3));
        assertFalse(bst.FindNodeByKey(3).NodeHasKey);
        assertEquals(2, rootNode.LeftChild.NodeKey);
        assertNull(rootNode.LeftChild.LeftChild);
        assertNull(rootNode.LeftChild.RightChild);
        assertEquals(2, bst.Count());
    }

    @Test
    public void deleteTestWhenNodeHasTwoNodesAndSuccessorNodeIsSheet() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(10, 100);
        bst.AddKeyValue(6, 66);
        bst.AddKeyValue(15, 150);
        bst.AddKeyValue(13, 130);
        bst.AddKeyValue(11, 110);
        bst.AddKeyValue(14, 140);
        bst.AddKeyValue(17, 170);


        assertEquals(11, bst.Count());
        assertTrue(bst.FindNodeByKey(10).NodeHasKey);
        assertTrue(bst.DeleteNodeByKey(10));
        assertEquals(10, bst.Count());
        assertFalse(bst.FindNodeByKey(10).NodeHasKey);
        assertEquals(11, rootNode.RightChild.NodeKey);
        assertEquals(6, rootNode.RightChild.LeftChild.NodeKey);

        assertEquals(15, rootNode.RightChild.RightChild.NodeKey);
        assertEquals(13, rootNode.RightChild.RightChild.LeftChild.NodeKey);
        assertEquals(14, rootNode.RightChild.RightChild.LeftChild.RightChild.NodeKey);
        assertEquals(17, rootNode.RightChild.RightChild.RightChild.NodeKey);
    }

    @Test
    public void deleteTestWhenNodeHasTwoNodesAndSuccessorNodeIsNotSheet() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(6, 66);
        bst.AddKeyValue(5, 55);
        bst.AddKeyValue(7, 77);
        bst.AddKeyValue(10, 100);
        bst.AddKeyValue(9, 99);
        assertEquals(9, bst.Count());
        assertTrue(bst.FindNodeByKey(6).NodeHasKey);
        assertTrue(bst.DeleteNodeByKey(6));
        assertEquals(8, bst.Count());
        assertFalse(bst.FindNodeByKey(6).NodeHasKey);
        assertEquals(7, rootNode.RightChild.NodeKey);
        assertEquals(5, rootNode.RightChild.LeftChild.NodeKey);
        assertEquals(10, rootNode.RightChild.RightChild.NodeKey);
        assertEquals(9, rootNode.RightChild.RightChild.LeftChild.NodeKey);
        assertNull(rootNode.RightChild.RightChild.LeftChild.LeftChild);
        assertNull(rootNode.RightChild.RightChild.LeftChild.RightChild);
    }

    @Test
    public void deleteLastNode() {
        assertEquals(1, bst.Count());
        assertTrue(bst.FindNodeByKey(4).NodeHasKey);
        assertTrue(bst.DeleteNodeByKey(4));
        assertEquals(0, bst.Count());
        assertNull(bst.Root);
    }

    @Test
    public void countTest() {
        assertEquals(1, bst.Count());

        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(6, 66);
        bst.AddKeyValue(5, 55);
        bst.AddKeyValue(7, 77);
        bst.AddKeyValue(10, 100);
        bst.AddKeyValue(9, 99);

        assertEquals(9, bst.Count());
    }
    @Test
    public void countTestWhenDeleteLastNode() {
        assertEquals(1, bst.Count());

        bst.DeleteNodeByKey(4);
        assertEquals(0, bst.Count());
    }


    @Test
    public void countTestWhenAddNodeToEmptyBst() {
        assertEquals(1, bst.Count());

        bst.DeleteNodeByKey(4);
        bst.AddKeyValue(3, 33);
        assertEquals(1, bst.Count());
    }
}