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
        int[] arr = new int[] {12, 45, 23, 66, 4, 47, 50, 81, 111, 95, 120, 41, 28, 19, 92};
        heap.MakeHeap(arr, 4);

        assertEquals(111, heap.GetMax());
    }

    @Test
    public void MakeHeapTest() {
        int[] arr = new int[] {12, 45, 23, 66, 4, 47, 50, 81, 111, 95, 120, 41, 28, 19, 92};
        heap.MakeHeap(arr, 3);

        assertEquals(15, heap.HeapArray.length);

        assertEquals(120, heap.HeapArray[0]);
        assertEquals(111, heap.HeapArray[1]);
        assertEquals(92, heap.HeapArray[2]);
        assertEquals(66, heap.HeapArray[3]);
        assertEquals(95, heap.HeapArray[4]);
        assertEquals(41, heap.HeapArray[5]);
        assertEquals(50, heap.HeapArray[6]);
        assertEquals(12, heap.HeapArray[7]);
        assertEquals(45, heap.HeapArray[8]);
        assertEquals(4, heap.HeapArray[9]);
        assertEquals(81, heap.HeapArray[10]);
        assertEquals(23, heap.HeapArray[11]);
        assertEquals(28, heap.HeapArray[12]);
        assertEquals(19, heap.HeapArray[13]);
        assertEquals(47, heap.HeapArray[14]);
    }

    @Test
    public void AddTestWhenHeapWhenHeapFilled() {
        int[] arr = new int[] {12, 45, 23, 66, 4, 47, 50, 81, 111, 95, 120, 41, 28, 19, 92};
        heap.MakeHeap(arr, 3);

        assertFalse(heap.Add(88));
    }

    @Test
    public void AddTestWhenHeapWhenHeapIsNotFilledAndKeyAddInMiddle() {
        int[] arr = new int[] {12, 45, 23, 66, 4, 47, 50, 81, 111, 95, 120, 41, 28, 19};
        heap.MakeHeap(arr, 3);

        assertTrue(heap.Add(110));
        assertEquals(110, heap.HeapArray[2]);
        assertEquals(41, heap.HeapArray[5]);
        assertEquals(50, heap.HeapArray[6]);
        assertEquals(23, heap.HeapArray[11]);
        assertEquals(28, heap.HeapArray[12]);
        assertEquals(19, heap.HeapArray[13]);
        assertEquals(47, heap.HeapArray[14]);
    }

    @Test
    public void AddTestWhenHeapWhenHeapIsNotFilledAndKeyAddInRoot() {
        int[] arr = new int[] {12, 45, 23, 66, 4, 47, 50, 81, 111, 95, 120, 41, 28, 19};
        heap.MakeHeap(arr, 3);

        assertTrue(heap.Add(150));
        assertEquals(150, heap.HeapArray[0]);
        assertEquals(120, heap.HeapArray[2]);
        assertEquals(41, heap.HeapArray[5]);
        assertEquals(50, heap.HeapArray[6]);
        assertEquals(23, heap.HeapArray[11]);
        assertEquals(28, heap.HeapArray[12]);
        assertEquals(19, heap.HeapArray[13]);
        assertEquals(47, heap.HeapArray[14]);
    }

    @Test
    public void AddTestTwoWhenHeapWhenHeapIsNotFilledAndKeyAddInRoot() {
        int[] arr = new int[] {12, 45, 23, 66, 4, 47, 50, 81};
        heap.MakeHeap(arr, 3);

        assertTrue(heap.Add(150));
        assertEquals(150, heap.HeapArray[0]);
        assertEquals(81, heap.HeapArray[1]);
        assertEquals(50, heap.HeapArray[2]);
        assertEquals(66, heap.HeapArray[3]);
        assertEquals(4, heap.HeapArray[4]);
        assertEquals(23, heap.HeapArray[5]);
        assertEquals(47, heap.HeapArray[6]);
        assertEquals(12, heap.HeapArray[7]);
        assertEquals(45, heap.HeapArray[8]);
    }
}