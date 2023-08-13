import java.util.*;

public class SimpleTreeNode<T>
{
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null
    public int level;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        if (!getAllNodes(Root).contains(ParentNode))
            return;

        if (ParentNode.Children == null)
            ParentNode.Children = new ArrayList<>();

        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
        // ваш код добавления нового дочернего узла существующему ParentNode
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        if (NodeToDelete == Root)
            return;

        if (getAllNodes(Root).contains(NodeToDelete)) {
            NodeToDelete.Parent.Children.remove(NodeToDelete);
            NodeToDelete.Parent = null;
        }
        // ваш код удаления существующего узла NodeToDelete
    }

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        return getAllNodes(Root);
        // ваш код выдачи всех узлов дерева в определённом порядке
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val)
    {
        List<SimpleTreeNode<T>> foundNodes = new ArrayList<>();

        for (SimpleTreeNode<T> currentNode: getAllNodes(Root)) {
            if (currentNode.NodeValue == val)
                foundNodes.add(currentNode);
        }
        // ваш код поиска узлов по значению
        return foundNodes;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
    }

    public int Count()
    {
        return getAllNodes(Root).size();
        // количество всех узлов в дереве
    }

    public int LeafCount()
    {
        return (int) getAllNodes(Root)
                .stream()
                .filter(node -> node.Children == null || node.Children.isEmpty())
                .count();
        // количество листьев в дереве
    }

    public void setNodesLevel() {
        setNodesLevel(Root, 1);
    }

    private void setNodesLevel(SimpleTreeNode<T> currentNode, int levelNum) {
        currentNode.level = levelNum;

        if (currentNode.Children == null)
            return;

        for (SimpleTreeNode<T> iterateNode: currentNode.Children) {
            setNodesLevel(iterateNode, levelNum + 1);
        }
    }

    private List<SimpleTreeNode<T>> getAllNodes(SimpleTreeNode<T> targetNode) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (targetNode == Root)
            nodes.add(targetNode);

        if (targetNode.Children == null)
            return nodes;

        nodes.addAll(targetNode.Children);

        for (SimpleTreeNode<T> currentNode: targetNode.Children) {
            nodes.addAll(getAllNodes(currentNode));
        }
        return nodes;
    }
}