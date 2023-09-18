import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    private final Heap heap = new Heap();

    @Test
    public void getMaxWhenHeapIsEmpty() {
        assertEquals(-1, heap.GetMax());
    }

    @Test
    public void getMaxWhenHeapSizeOne() {
        heap.MakeHeap(new int[] {1}, 0);
        assertEquals(0, heap.GetMax());
    }


    @Test
    public void getMaxWhenHeapIsNotEmpty() {
        int[] arr = new int[] {12, 5, 13, 6, 14, 7, 15, 8, 11, 9, 10, 4, 2, 1, 3};
        heap.MakeHeap(arr, 4);

        assertEquals(14, heap.GetMax());
    }

    @Test
    public void MakeHeapTest() {
        int[] arr = new int[] {12, 5, 13, 6, 14, 7, 15, 8, 11, 9, 10, 4, 2, 1, 3};
        heap.MakeHeap(arr, 3);

        assertEquals(15, heap.HeapArray.length);

        assertEquals(15, heap.HeapArray[0]);
        assertEquals(13, heap.HeapArray[1]);
        assertEquals(14, heap.HeapArray[2]);
        assertEquals(11, heap.HeapArray[3]);
        assertEquals(10, heap.HeapArray[4]);
        assertEquals(7, heap.HeapArray[5]);
        assertEquals(12, heap.HeapArray[6]);
        assertEquals(5, heap.HeapArray[7]);
        assertEquals(8, heap.HeapArray[8]);
        assertEquals(6, heap.HeapArray[9]);
        assertEquals(9, heap.HeapArray[10]);
        assertEquals(4, heap.HeapArray[11]);
        assertEquals(2, heap.HeapArray[12]);
        assertEquals(1, heap.HeapArray[13]);
        assertEquals(3, heap.HeapArray[14]);
    }

    @Test
    public void AddTestWhenHeapWhenHeapFilled() {
        int[] arr = new int[] {12, 5, 13, 6, 14, 7, 15, 8, 11, 9, 10, 4, 2, 1, 3};
        heap.MakeHeap(arr, 3);

        assertFalse(heap.Add(88));
    }

    @Test
    public void AddTestWhenHeapWhenHeapIsNotFilled() {
        int[] arr = new int[] {12, 5, 13, 6, 14, 7, 15, 8, 11, 9, 10, 4, 2, 1};
        heap.MakeHeap(arr, 3);

        assertTrue(heap.Add(3));
        assertEquals(3, heap.HeapArray[14]);
    }
}