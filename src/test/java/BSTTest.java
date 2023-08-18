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
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(5, 55);
        bst.AddKeyValue(5, 55);

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
    public void deleteTest() {
        bst.AddKeyValue(3, 33);
        bst.AddKeyValue(1, 11);
        bst.AddKeyValue(2, 22);
        bst.AddKeyValue(6, 66);
        bst.AddKeyValue(5, 55);
        bst.AddKeyValue(7, 77);
        bst.AddKeyValue(10, 100);
        bst.AddKeyValue(9, 99);

        assertTrue(bst.FindNodeByKey(6).NodeHasKey);
        assertTrue(bst.DeleteNodeByKey(6));
        assertFalse(bst.FindNodeByKey(6).NodeHasKey);
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


}