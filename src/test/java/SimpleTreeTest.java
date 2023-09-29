import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    private final SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    private final SimpleTree<Integer> simpleTree = new SimpleTree<>(rootNode);

    @Test
    public void addChildTest() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);

        assertEquals(1, simpleTree.Root.Children.size());
        assertEquals(2, childNode.Children.size());
        assertEquals(simpleTree.Root.Children.get(0), childNode);
        assertEquals(childNode.Children.get(0), childNodeSecond);
        assertEquals(childNode.Children.get(1), childNodeThird);
    }

    @Test
    public void whenNodeToDeleteIsRoot() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        simpleTree.AddChild(rootNode, childNode);
        simpleTree.DeleteNode(rootNode);

        assertEquals(1, simpleTree.Root.Children.size());
        assertEquals(simpleTree.Root.Children.get(0), childNode);
    }

    @Test
    public void whenNodeToDeleteIsNotRoot() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.DeleteNode(childNode);

        assertEquals(0, simpleTree.Root.Children.size());
    }

    @Test
    public void whenSimpleTreeHasOnlyRootNode_thenGetAllNodes() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);

        List<SimpleTreeNode<Integer>> foundNodes = simpleTree.GetAllNodes();
        assertEquals(3, foundNodes.size());
    }

    @Test
    public void whenSimpleTreeHasSomeNodes_thenGetAllNodes() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);

        List<SimpleTreeNode<Integer>> foundNodes = simpleTree.GetAllNodes();
        assertEquals(4, foundNodes.size());
    }

    @Test
    public void whenSimpleTreeHasSomeWithSameValues_FindNodesByValue() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);
        SimpleTreeNode<Integer> childNodeFourth = new SimpleTreeNode<>(4, childNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);
        simpleTree.AddChild(childNodeSecond, childNodeFourth);

        List<SimpleTreeNode<Integer>> foundNodes = simpleTree.FindNodesByValue(4);
        assertEquals(2, foundNodes.size());
    }

    @Test
    public void whenSimpleTreeHasNotSomeWithSameValues_thenFindNodesByValue() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);
        SimpleTreeNode<Integer> childNodeFourth = new SimpleTreeNode<>(5, childNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);
        simpleTree.AddChild(childNodeSecond, childNodeFourth);

        List<SimpleTreeNode<Integer>> foundNodes = simpleTree.FindNodesByValue(4);
        assertEquals(1, foundNodes.size());
    }

    @Test
    public void whenSimpleTreeHasNotNodeWithValues_thenFindNodesByValue() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);
        SimpleTreeNode<Integer> childNodeFourth = new SimpleTreeNode<>(5, childNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);
        simpleTree.AddChild(childNodeSecond, childNodeFourth);

        List<SimpleTreeNode<Integer>> foundNodes = simpleTree.FindNodesByValue(6);
        assertEquals(0, foundNodes.size());
    }
    @Test
    public void moveNodeTest() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);
        SimpleTreeNode<Integer> childNodeFourth = new SimpleTreeNode<>(5, childNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);
        simpleTree.AddChild(childNodeSecond, childNodeFourth);

        simpleTree.MoveNode(childNodeSecond, rootNode);
        assertEquals(1, childNode.Children.size());
        assertEquals(2, rootNode.Children.size());
        assertEquals(childNode, rootNode.Children.get(0));
        assertEquals(childNodeSecond, rootNode.Children.get(1));
    }

    @Test
    public void whenSimpleTreeHasOnlyRootNode_thenCount() {
        assertEquals(1, simpleTree.Count());
    }

    @Test
    public void whenSimpleTreeHasSomeNodes_thenCount() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);
        SimpleTreeNode<Integer> childNodeFourth = new SimpleTreeNode<>(5, childNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);
        simpleTree.AddChild(childNodeSecond, childNodeFourth);

        assertEquals(5, simpleTree.Count());
    }
    @Test
    public void whenSimpleTreeHasSomeNodesAfterDelete_thenCount() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);
        SimpleTreeNode<Integer> childNodeFourth = new SimpleTreeNode<>(5, childNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);
        simpleTree.AddChild(childNodeSecond, childNodeFourth);

        simpleTree.DeleteNode(childNodeSecond);

        assertEquals(3, simpleTree.Count());
    }

    @Test
    public void whenSimpleTreeHasOnlyRootNode_thenLeafCount() {
        assertEquals(1, simpleTree.LeafCount());
    }

    @Test
    public void whenSimpleTreeHasSomeNodes_thenLeafCount() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);
        SimpleTreeNode<Integer> childNodeFourth = new SimpleTreeNode<>(5, childNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);
        simpleTree.AddChild(childNodeSecond, childNodeFourth);
        assertEquals(2, simpleTree.LeafCount());

        simpleTree.DeleteNode(childNode);
        assertEquals(1, simpleTree.LeafCount());
    }

    @Test
    public void setNodesLevelTest() {
        SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> childNodeSecond = new SimpleTreeNode<>(3, childNode);
        SimpleTreeNode<Integer> childNodeThird = new SimpleTreeNode<>(4, rootNode);
        SimpleTreeNode<Integer> childNodeFourth = new SimpleTreeNode<>(5, childNode);

        simpleTree.AddChild(rootNode, childNode);
        simpleTree.AddChild(childNode, childNodeSecond);
        simpleTree.AddChild(childNode, childNodeThird);
        simpleTree.AddChild(childNodeSecond, childNodeFourth);

        simpleTree.setNodesLevel();

        assertEquals(1, rootNode.level);
        assertEquals(2, childNode.level);
        assertEquals(3, childNodeSecond.level);
        assertEquals(3, childNodeThird.level);
        assertEquals(4, childNodeFourth.level);
    }

    @Test
    public void evenTreesTestWhenTreeEmpty() {
        ArrayList<Integer> actualResult = simpleTree.EvenTrees();

        assertEquals(Collections.EMPTY_LIST, actualResult);
    }

    @Test
    public void evenTreesFirstTest() {
        SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, rootNode);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, node3);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, node2);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, rootNode);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, node2);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, node6);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, node8);
        SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, node8);
        SimpleTree<Integer> simpleTree = new SimpleTree<>(rootNode);

        simpleTree.AddChild(rootNode, node2);
        simpleTree.AddChild(rootNode, node3);
        simpleTree.AddChild(node3, node4);
        simpleTree.AddChild(node2, node5);
        simpleTree.AddChild(rootNode, node6);
        simpleTree.AddChild(node2, node7);
        simpleTree.AddChild(node6, node8);
        simpleTree.AddChild(node8, node9);
        simpleTree.AddChild(node8, node10);

        ArrayList<Integer> actualResult = simpleTree.EvenTrees();

        assertEquals(Arrays.asList(1, 3, 1, 6), actualResult);
    }

    @Test
    public void evenTreesSecondTest() {
        SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, rootNode);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, node2);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, node3);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, node4);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, node5);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, node6);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, node7);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, node8);
        SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, node9);
        SimpleTree<Integer> simpleTree = new SimpleTree<>(rootNode);

        simpleTree.AddChild(rootNode, node2);
        simpleTree.AddChild(node2, node3);
        simpleTree.AddChild(node3, node4);
        simpleTree.AddChild(node4, node5);
        simpleTree.AddChild(node5, node6);
        simpleTree.AddChild(node6, node7);
        simpleTree.AddChild(node7, node8);
        simpleTree.AddChild(node8, node9);
        simpleTree.AddChild(node9, node10);

        ArrayList<Integer> actualResult = simpleTree.EvenTrees();

        assertEquals(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9), actualResult);
    }

}