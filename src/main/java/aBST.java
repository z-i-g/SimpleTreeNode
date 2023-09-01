import java.util.*;

class aBST
{
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = (int) Math.pow(2, depth);
        Tree = new Integer[ tree_size + tree_size - 1 ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key)
    {
        if (Tree[0] == null)
            return 0;
        return FindKeyIndexRecursively(0, key);
    }

    private Integer FindKeyIndexRecursively(int currentIndex, int needFoundKey)
    {
        if (Tree[currentIndex] == needFoundKey)
            return currentIndex;

        int leftChildIndex = 2 * currentIndex + 1;
        int rightChildIndex = 2 * currentIndex + 2;

        if (leftChildIndex >= Tree.length && rightChildIndex >= Tree.length)
            return null;

        if (Tree[currentIndex] > needFoundKey && Tree[leftChildIndex] != null)
            return FindKeyIndexRecursively(2 * currentIndex + 1, needFoundKey);

        if (Tree[currentIndex] < needFoundKey && Tree[rightChildIndex] != null)
            return FindKeyIndexRecursively(2 * currentIndex + 2, needFoundKey);

        return Tree[currentIndex] > needFoundKey ? -leftChildIndex : -rightChildIndex; // не найден
    }

    public int AddKey(int key)
    {
        Integer foundIndex = FindKeyIndex(key);
        if (foundIndex == null)
            return -1;


        if (foundIndex < 0) {
            foundIndex = Math.abs(foundIndex);
            Tree[foundIndex] = key;
            return foundIndex;
        }

        if (foundIndex == 0)
            Tree[foundIndex] = key;

        return foundIndex;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }
}