import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTNodeTest {

    @Test
    public void generateTreeTest() {
        int[] arr = new int[] {6, 2, 5, 3, 4, 1, 7, 11, 8, 12, 9, 13, 10, 15, 14};
        BalancedBST balancedBST = new BalancedBST();
        balancedBST.GenerateTree(arr);

        assertEquals(8, balancedBST.Root.NodeKey);
        assertEquals(4, balancedBST.Root.LeftChild.NodeKey);
        assertEquals(12, balancedBST.Root.RightChild.NodeKey);
        assertEquals(2, balancedBST.Root.LeftChild.LeftChild.NodeKey);
        assertEquals(6, balancedBST.Root.LeftChild.RightChild.NodeKey);
        assertEquals(10, balancedBST.Root.RightChild.LeftChild.NodeKey);
        assertEquals(14, balancedBST.Root.RightChild.RightChild.NodeKey);
        assertEquals(1, balancedBST.Root.LeftChild.LeftChild.LeftChild.NodeKey);
        assertEquals(3, balancedBST.Root.LeftChild.LeftChild.RightChild.NodeKey);
        assertEquals(5, balancedBST.Root.LeftChild.RightChild.LeftChild.NodeKey);
        assertEquals(7, balancedBST.Root.LeftChild.RightChild.RightChild.NodeKey);
        assertEquals(9, balancedBST.Root.RightChild.LeftChild.LeftChild.NodeKey);
        assertEquals(11, balancedBST.Root.RightChild.LeftChild.RightChild.NodeKey);
        assertEquals(13, balancedBST.Root.RightChild.RightChild.LeftChild.NodeKey);
        assertEquals(15, balancedBST.Root.RightChild.RightChild.RightChild.NodeKey);

    }

    @Test
    public void treeIsNotBalancedTest() {
        BSTNode bstNode1 = new BSTNode(8, null);
        BSTNode bstNode2 = new BSTNode(6, bstNode1);
        bstNode1.LeftChild = bstNode2;
        BSTNode bstNode3 = new BSTNode(4, bstNode2);
        bstNode2.LeftChild = bstNode3;
        BSTNode bstNode4 = new BSTNode(2, bstNode3);
        bstNode3.LeftChild = bstNode4;
        bstNode4.LeftChild = new BSTNode(7, bstNode4);

        BalancedBST balancedBST = new BalancedBST();
        assertFalse(balancedBST.IsBalanced(bstNode1));
    }



    @Test
    public void treeIsBalancedTest() {
        BSTNode bstNode1 = new BSTNode(8, null);
        BSTNode bstNode2 = new BSTNode(6, bstNode1);
        bstNode1.LeftChild = bstNode2;
        BSTNode bstNode3 = new BSTNode(4, bstNode1);
        bstNode1.RightChild = bstNode3;
        bstNode2.LeftChild = new BSTNode(2, bstNode2);
        bstNode3.RightChild = new BSTNode(7, bstNode3);

        BalancedBST balancedBST = new BalancedBST();
        assertTrue(balancedBST.IsBalanced(bstNode1));
    }

}