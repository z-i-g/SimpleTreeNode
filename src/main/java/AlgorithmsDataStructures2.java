import java.util.*;

public class AlgorithmsDataStructures2
{
    public static int[] GenerateBBSTArray(int[] a)
    {
        Arrays.sort(a);
        int[] bbstArray = new int[a.length];
        int root = a.length/2;
        bbstArray[0] = a[root];
        fillBbstArray(bbstArray, a, 0, root, 0);
        fillBbstRArray(bbstArray, a, root - 1, a.length, 0);
        return bbstArray;
    }

    private static void fillBbstArray(int[] bbstArray, int[] sourceArray, int startIndex, int endIndex, int currentRoot) {
        int midIndex = (startIndex + endIndex) / 2;
        if (startIndex <= endIndex) {
            int leftChildIndex = 2 * currentRoot + 1;
            if (leftChildIndex >= sourceArray.length)
                return;
            bbstArray[leftChildIndex] = sourceArray[midIndex];
            fillBbstArray(bbstArray, sourceArray, startIndex, midIndex - 1, leftChildIndex);
        }

    }

    private static void fillBbstRArray(int[] bbstArray, int[] sourceArray, int startIndex, int endIndex, int currentRoot) {
        int midIndex = (startIndex + endIndex) / 2;
        int leftChildIndex = 2 * currentRoot + 2;
        if (leftChildIndex >= sourceArray.length)
            return;
        bbstArray[leftChildIndex] = sourceArray[midIndex];
        fillBbstArray(bbstArray, sourceArray, midIndex - 1, endIndex, leftChildIndex);
    }
}