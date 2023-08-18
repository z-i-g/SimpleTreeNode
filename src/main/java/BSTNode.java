import java.io.*;
import java.util.*;


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
    private int size = 0;

    public BST(BSTNode<T> node)
    {
        Root = node;
        size++;
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
        BSTFind<T> bstFind = new BSTFind<>();
        if (Root == null)
            return bstFind;

        BSTNode<T> fundNode = findNode(Root, key);
        bstFind.Node = fundNode;

        if (fundNode.NodeKey == key)
            bstFind.NodeHasKey = true;

        if (fundNode.NodeKey > key)
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
        if (bstFind.NodeHasKey)
            return false;

        if (bstFind.ToLeft) {
            bstFind.Node.LeftChild = new BSTNode<>(key, val, bstFind.Node);
            size++;
            return true;
        }

        bstFind.Node.RightChild = new BSTNode<>(key, val, bstFind.Node);
        size++;
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
        BSTFind<T> fundNode = FindNodeByKey(key);
        if (!fundNode.NodeHasKey)
            return false;

        if (fundNode.Node.RightChild == null) {
            fundNode.Node = fundNode.Node.LeftChild;
            size--;
            return true;
        }

        BSTNode<T> minNode = FinMinMax(fundNode.Node.RightChild, false);

        if (minNode.RightChild == null && minNode.LeftChild == null) {
            minNode.LeftChild = fundNode.Node.LeftChild;
            minNode.RightChild = fundNode.Node.RightChild;
            fundNode.Node = minNode;
            size--;
            return true;
        }

        if (minNode.RightChild != null) {
            minNode = minNode.RightChild;

            BSTNode<T> fr = fundNode.Node.RightChild;
            BSTNode<T> fl = fundNode.Node.LeftChild;

            fundNode.Node = minNode;
            fundNode.Node.RightChild = fr;
            fundNode.Node.LeftChild = fl;
            size--;
            return true;
        }

        // удаляем узел по ключу
        return false; // если узел не найден
    }

    public int Count()
    {
        return size; // количество узлов в дереве
    }

}