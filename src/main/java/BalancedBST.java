import java.util.*;

class BSTNode
{
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int     Level; // глубина узла

    public BSTNode(int key, BSTNode parent)
    {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST
{
    public BSTNode Root; // корень дерева

    public BalancedBST()
    {
        Root = null;
    }

    public void GenerateTree(int[] a)
    {
        Arrays.sort(a);
        fillTree(Root, a, 0, a.length - 1, 0);
    }

    private BSTNode fillTree(BSTNode currentRoot, int[] sourceArray, int startIndex, int endIndex, int currentLevel) {
        if (startIndex > endIndex)
            return null;
        int midIndex = (startIndex + endIndex) / 2;

        BSTNode node = new BSTNode(sourceArray[midIndex], currentRoot);
        node.Level = currentLevel;
        if (Root == null)
            Root = node;

        currentLevel++;
        node.LeftChild = fillTree(node, sourceArray, startIndex, midIndex - 1, currentLevel);
        node.RightChild = fillTree(node, sourceArray, midIndex + 1, endIndex, currentLevel);

        return node;
    }

    public boolean IsBalanced(BSTNode root_node)
    {
        return checkBalancedRecursively(root_node) != -1; // сбалансировано ли дерево с корнем root_node
    }

    private int checkBalancedRecursively(BSTNode currentNode) {
        if (currentNode == null)
            return 0;

        int leftHeight = checkBalancedRecursively(currentNode.LeftChild);
        int rightHeight = checkBalancedRecursively(currentNode.RightChild);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return 1 + Math.max(leftHeight, rightHeight);
    }
}  