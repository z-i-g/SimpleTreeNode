import org.junit.jupiter.api.Test;

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
}