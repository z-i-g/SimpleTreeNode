import java.util.*;

public class AlgorithmsDataStructures2
{
    public static int[] GenerateBBSTArray(int[] a)
    {
        Arrays.sort(a);
        int[] bbstArray = new int[a.length];
        fillBbstArray(bbstArray, a, 0, a.length - 1, 0);
        return bbstArray;
    }

    private static void fillBbstArray(int[] bbstArray, int[] sourceArray, int startIndex, int endIndex, int currentRoot) {
        if (startIndex > endIndex)
            return;
        int midIndex = (startIndex + endIndex) / 2;
        bbstArray[currentRoot] = sourceArray[midIndex];

        fillBbstArray(bbstArray, sourceArray, startIndex, midIndex - 1, 2 * currentRoot + 1);
        fillBbstArray(bbstArray, sourceArray, midIndex + 1, endIndex, 2 * currentRoot + 2);
    }
}