import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {
    @Test
    public void GenerateBBSTArrayTest() {
        int[] arr = new int[] {6, 2, 5, 3, 4, 1, 7, 11, 8, 12, 9, 13, 10, 15, 14};
        int[] bbstArray = AlgorithmsDataStructures2.GenerateBBSTArray(arr);

        assertEquals(8, bbstArray[0]);
        assertEquals(4, bbstArray[1]);
        assertEquals(12, bbstArray[2]);
        assertEquals(2, bbstArray[3]);
        assertEquals(6, bbstArray[4]);
        assertEquals(10, bbstArray[5]);
        assertEquals(14, bbstArray[6]);
        assertEquals(1, bbstArray[7]);
        assertEquals(3, bbstArray[8]);
        assertEquals(5, bbstArray[9]);
        assertEquals(7, bbstArray[10]);
        assertEquals(9, bbstArray[11]);
        assertEquals(11, bbstArray[12]);
        assertEquals(13, bbstArray[13]);
        assertEquals(15, bbstArray[14]);
    }
}