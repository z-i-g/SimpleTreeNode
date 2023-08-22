class BSTNode<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок	

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T>
{
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() { Node = null; }
}

class BST<T>
{
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
        BSTFind<T> bstFind = new BSTFind<>();
        if (Root == null)
            return bstFind;

        BSTNode<T> foundNode = findNode(Root, key);
        bstFind.Node = foundNode;

        if (foundNode.NodeKey == key)
            bstFind.NodeHasKey = true;

        if (foundNode.NodeKey > key)
            bstFind.ToLeft = true;

        // ищем в дереве узел и сопутствующую информацию по ключу
        return bstFind;
    }

    private BSTNode<T> findNode(BSTNode<T> currentNode, int key) {
        if (currentNode.NodeKey == key)
            return currentNode;

        if (currentNode.RightChild == null && currentNode.LeftChild == null)
            return currentNode;

        if (currentNode.NodeKey > key && currentNode.LeftChild != null)
            return findNode(currentNode.LeftChild, key);

        if (currentNode.NodeKey < key && currentNode.RightChild != null)
            return findNode(currentNode.RightChild, key);

        return currentNode;
    }

    public boolean AddKeyValue(int key, T val)
    {
        BSTFind<T> bstFind = FindNodeByKey(key);

        if (bstFind.Node == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }

        if (bstFind.NodeHasKey)
            return false;

        if (bstFind.ToLeft) {
            bstFind.Node.LeftChild = new BSTNode<>(key, val, bstFind.Node);
            return true;
        }

        bstFind.Node.RightChild = new BSTNode<>(key, val, bstFind.Node);
        return true;
        // добавляем ключ-значение в дерево
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        if (FindMax && FromNode.RightChild != null)
            return FinMinMax(FromNode.RightChild, true);

        if (!FindMax && FromNode.LeftChild != null)
            return FinMinMax(FromNode.LeftChild, false);
        // ищем максимальный/минимальный ключ в поддереве
        return FromNode;
    }

    public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> deletedNode = FindNodeByKey(key);
        if (!deletedNode.NodeHasKey)
            return false;

        if (deletedNode.Node.RightChild == null && deletedNode.Node.LeftChild == null)
            return deleteSheet(deletedNode.Node);

        if (deletedNode.Node.RightChild == null)
            return deleteLeftChild(deletedNode.Node);

        if (deletedNode.Node.LeftChild == null)
            return deleteRightChild(deletedNode.Node);

        return deleteNodeWithTwoHeirs(deletedNode.Node);
    }

    private boolean deleteLeftChild(BSTNode<T> deletedNode) {
        deletedNode.Parent.LeftChild = deletedNode.LeftChild;
        deletedNode.LeftChild.Parent = deletedNode.Parent;
        return true;
    }

    private boolean deleteRightChild(BSTNode<T> deletedNode) {
        deletedNode.Parent.RightChild = deletedNode.RightChild;
        deletedNode.RightChild.Parent = deletedNode.Parent;
        return true;
    }

    private boolean deleteNodeWithTwoHeirs(BSTNode<T> deletedNode) {
        BSTNode<T> minNode = FinMinMax(deletedNode.RightChild, false);

        if (minNode.LeftChild == null && minNode.RightChild == null)
          return deleteSheetAndReplace(deletedNode, minNode);

        if (deletedNode.Parent.NodeKey < minNode.NodeKey)
            deletedNode.Parent.RightChild = minNode;

        if (deletedNode.Parent.NodeKey > minNode.NodeKey)
            deletedNode.Parent.LeftChild = minNode;

        if (minNode.Parent.NodeKey < minNode.RightChild.NodeKey)
            minNode.Parent.RightChild = minNode.RightChild;

        if (minNode.Parent.NodeKey > minNode.RightChild.NodeKey)
            minNode.Parent.LeftChild = minNode.RightChild;

        minNode.RightChild.Parent = minNode.Parent;
        minNode.LeftChild = deletedNode.LeftChild;
        minNode.RightChild = deletedNode.RightChild;
        deletedNode.LeftChild.Parent = minNode;
        deletedNode.RightChild.Parent = minNode;
        minNode.Parent = deletedNode.Parent;
        return true;
    }

    private boolean deleteSheet(BSTNode<T> deletedNode) {
        if (deletedNode == Root) {
            Root = null;
            return true;
        }
        if (deletedNode.Parent.NodeKey < deletedNode.NodeKey)
            deletedNode.Parent.RightChild = null;
        if (deletedNode.Parent.NodeKey > deletedNode.NodeKey)
            deletedNode.Parent.LeftChild = null;
        return true;
    }

    private boolean deleteSheetAndReplace(BSTNode<T> deletedNode, BSTNode<T> successorNode) {
        if (deletedNode == Root) {
            Root = null;
            return true;
        }
        if (deletedNode.Parent.NodeKey < successorNode.NodeKey)
            deletedNode.Parent.RightChild = successorNode;
        if (deletedNode.Parent.NodeKey > successorNode.NodeKey)
            deletedNode.Parent.LeftChild = successorNode;

        if (successorNode.Parent.NodeKey < successorNode.NodeKey)
            successorNode.Parent.RightChild = null;
        if (successorNode.Parent.NodeKey > successorNode.NodeKey)
            successorNode.Parent.LeftChild = null;


        successorNode.Parent = deletedNode.Parent;
        successorNode.LeftChild = deletedNode.LeftChild;
        successorNode.RightChild = deletedNode.RightChild;
        successorNode.LeftChild.Parent = successorNode;
        successorNode.RightChild.Parent = successorNode;
        return true;
    }

    public int Count()
    {
        if (Root != null) {
            return countRecursively(Root, 1);
        }
        return 0; // количество узлов в дереве
    }

    private int countRecursively(BSTNode<T> currentNode, int iterateSize) {
        if (currentNode.RightChild != null)
            iterateSize = 1 + countRecursively(currentNode.RightChild, iterateSize);

        if (currentNode.LeftChild != null)
            iterateSize =  1 + countRecursively(currentNode.LeftChild, iterateSize);
        return iterateSize;
    }
}