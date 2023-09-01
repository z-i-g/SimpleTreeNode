import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class aBSTTest {


    aBST aBST = new aBST(2);

    @Test
    public void bstLengthTest() {
        aBST = new aBST(0);
        assertEquals(1, aBST.Tree.length);

        aBST = new aBST(1);
        assertEquals(3, aBST.Tree.length);

        aBST = new aBST(2);
        assertEquals(7, aBST.Tree.length);

        aBST = new aBST(3);
        assertEquals(15, aBST.Tree.length);
    }

    @Test
    public void findKeyIndexWhenTreeIsEmpty() {
        int foundKey = aBST.FindKeyIndex(1);

        assertEquals(0, foundKey);
    }

    @Test
    public void findKeyIndexWhenIndexIsFound() {
        assertEquals(0, aBST.AddKey(5));
        assertEquals(1, aBST.AddKey(3));
        assertEquals(3, aBST.AddKey(2));
        assertEquals(4, aBST.AddKey(4));
        assertEquals(2, aBST.AddKey(7));
        assertEquals(6, aBST.AddKey(8));

        assertEquals(-5, aBST.FindKeyIndex(6));
    }

    @Test
    public void findKeyIndexWhenIndexIsContains() {
        assertEquals(0, aBST.AddKey(5));
        assertEquals(1, aBST.AddKey(3));
        assertEquals(3, aBST.AddKey(2));
        assertEquals(4, aBST.AddKey(4));
        assertEquals(2, aBST.AddKey(7));
        assertEquals(6, aBST.AddKey(8));

        assertEquals(6, aBST.FindKeyIndex(8));

        assertEquals(0, aBST.AddKey(5));
    }

    @Test
    public void findKeyIndexWhenTreeIsFilled() {
        assertEquals(0, aBST.AddKey(3));
        assertEquals(1, aBST.AddKey(2));
        assertEquals(3, aBST.AddKey(1));
        assertEquals(2, aBST.AddKey(4));
        assertEquals(6, aBST.AddKey(5));

        Integer foundKey = aBST.FindKeyIndex(9);

        assertNull(foundKey);
    }

    @Test
    public void AddKeyWhenTreeIsEmpty() {
        int addedKey = aBST.AddKey(5);

        assertEquals(0, addedKey);
        assertEquals(5, aBST.Tree[0]);
    }

    @Test
    public void AddKeyWhenAddSomeKey() {
        assertEquals(0, aBST.AddKey(3));
        assertEquals(1, aBST.AddKey(2));
        assertEquals(3, aBST.AddKey(1));
        assertEquals(2, aBST.AddKey(4));
        assertEquals(6, aBST.AddKey(5));

        assertEquals(3, aBST.Tree[0]);
        assertEquals(2, aBST.Tree[1]);
        assertEquals(1, aBST.Tree[3]);
        assertEquals(4, aBST.Tree[2]);
        assertEquals(5, aBST.Tree[6]);
        assertNull(aBST.Tree[4]);
        assertNull(aBST.Tree[5]);
    }

    @Test
    public void AddKeyWhenAddEqualsKey() {
        assertEquals(0, aBST.AddKey(3));
        assertEquals(1, aBST.AddKey(2));
        assertEquals(3, aBST.AddKey(1));
        assertEquals(2, aBST.AddKey(4));
        assertEquals(6, aBST.AddKey(5));
        assertEquals(6, aBST.AddKey(5));

        assertEquals(3, aBST.Tree[0]);
        assertEquals(2, aBST.Tree[1]);
        assertEquals(1, aBST.Tree[3]);
        assertEquals(4, aBST.Tree[2]);
        assertEquals(5, aBST.Tree[6]);
        assertNull(aBST.Tree[4]);
        assertNull(aBST.Tree[5]);
    }

    @Test
    public void AddKeyWhenAddFiledToAdd() {
        assertEquals(0, aBST.AddKey(3));
        assertEquals(1, aBST.AddKey(2));
        assertEquals(3, aBST.AddKey(1));
        assertEquals(2, aBST.AddKey(4));
        assertEquals(6, aBST.AddKey(5));
        assertEquals(-1, aBST.AddKey(9));

        assertEquals(3, aBST.Tree[0]);
        assertEquals(2, aBST.Tree[1]);
        assertEquals(1, aBST.Tree[3]);
        assertEquals(4, aBST.Tree[2]);
        assertEquals(5, aBST.Tree[6]);
        assertNull(aBST.Tree[4]);
        assertNull(aBST.Tree[5]);
    }

}