import java.util.*;

class Heap
{
    public int [] HeapArray; // хранит неотрицательные числа-ключи

    public Heap() { HeapArray = null; }

    public void MakeHeap(int[] a, int depth)
    {
        int heapSize = (int) Math.pow(2, (depth + 1)) - 1;
        HeapArray = new int[heapSize];

        for (int key: a) {
            Add(key);
        }
        // создаём массив кучи HeapArray из заданного
        // размер массива выбираем на основе глубины depth
        // ...
    }

    public int GetMax()
    {
        if (HeapArray == null)
            return -1;
        int lastFilledIndex = findLastFilledIndex();
        if (lastFilledIndex == 0)
            return -1;
        HeapArray[0] = HeapArray[lastFilledIndex];
        HeapArray[lastFilledIndex] = 0;
        moveElementToDown(0);
        // вернуть значение корня и перестроить кучу
        return HeapArray[0]; // если куча пуста
    }

    public boolean Add(int key)
    {
        if (HeapArray == null)
            return false;
        int firstEmptyIndex = findFirstEmptyIndex();
        if (firstEmptyIndex == -1)
            return false;
        HeapArray[firstEmptyIndex] = key;
        moveElementToTop(firstEmptyIndex);

        // добавляем новый элемент key в кучу и перестраиваем её
        return true; // если куча вся заполнена
    }

    private int findFirstEmptyIndex() {
        for (int i = 0; i < HeapArray.length; i++) {
            if (HeapArray[i] == 0)
                return i;
        }
        return -1;
    }

    private int findLastFilledIndex() {
        for (int i = HeapArray.length - 1; i >= 0; i--) {
            if (HeapArray[i] != 0)
                return i;
        }
        return 0;
    }

    private void moveElementToTop(int currentIndex) {
        if (currentIndex == 0)
            return;
        boolean isRightElementIndex = currentIndex % 2 == 0;
        int currentRootIndex = (currentIndex - (isRightElementIndex ? 2 : 1)) / 2;

        if (HeapArray[currentRootIndex] > HeapArray[currentIndex])
            return;

        int temp = HeapArray[currentRootIndex];
        HeapArray[currentRootIndex] = HeapArray[currentIndex];
        HeapArray[currentIndex] = temp;
        moveElementToTop(currentRootIndex);
    }

    private void moveElementToDown(int currentIndex) {
        int leftElementIndex = 2 * currentIndex + 1;
        int rightElementIndex = 2 * currentIndex + 2;

        if (leftElementIndex > HeapArray.length - 1 && rightElementIndex > HeapArray.length - 1)
            return;

        if (leftElementIndex > HeapArray.length - 1 && HeapArray[currentIndex] > HeapArray[rightElementIndex])
            return;

        if (rightElementIndex > HeapArray.length - 1 && HeapArray[currentIndex] > HeapArray[leftElementIndex])
            return;

        if (HeapArray[currentIndex] >= HeapArray[leftElementIndex] && HeapArray[currentIndex] >= HeapArray[rightElementIndex])
            return;

        int maxNextElementIndex = HeapArray[leftElementIndex] > HeapArray[rightElementIndex] ? leftElementIndex : rightElementIndex;
        int temp = HeapArray[currentIndex];
        HeapArray[currentIndex] = HeapArray[maxNextElementIndex];
        HeapArray[maxNextElementIndex] = temp;
        moveElementToDown(maxNextElementIndex);
    }

}