import MyArrayList.MyArrayList;
import Gems.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest1
{
  private MyArrayList<Gem> testList;
  private Gem gem1,gem2,gem3;

  @BeforeEach
  public void setUp()
  {
    testList = new MyArrayList<Gem>(null);
    gem1 = new GenericGem(GemType.AMETHYST);
    gem2 = new GenericGem(GemType.CHILD);
    gem3 = new GenericGem(GemType.COW);
  }
  //Z
        @Test
        //Zero - empty list test
        void testEmptyOnCreation()
        {
          assertTrue(testList.isEmpty());
          assertEquals (0, testList.size());
        }
  //O

        @Test
        //One - add test
        public void testOneAdd()
        {
          testList.add(gem1);
          assertFalse(testList.isEmpty());
          assertEquals(1, testList.size());
          assertEquals(gem1, testList.get(0));
        }
  //M
        @Test
        //Many - add test
        public void testTwoAdd()
        {
          testList.add(gem1);
          testList.add(gem2);
          assertFalse(testList.isEmpty());
          assertEquals(2, testList.size());
          assertEquals(gem1, testList.get(0));
          assertEquals(gem2, testList.get(1));
        }
  //B
        @Test
        //Boundary - add test (at index 0 and at end)
        void testBoundariesIndexAdd()
        {
          testList.add(gem1);
          testList.add(0, gem2);
          testList.add(2, gem3);

          assertEquals(testList.get(0), gem2);
          assertEquals(testList.get(1), gem1);
          assertEquals(testList.get(2), gem3);
        }
  //I
        @Test
          //Interface - indexOf() should return the index of the specified element, or -1 if not found
        void testIndexOf()
        {
          testList.add(gem1);
          testList.add(gem2);

          assertEquals(0, testList.indexOf(gem1));
          assertEquals(-1, testList.indexOf(gem3));
        }
  //E
        @Test
        //Exception - add test
        void testExceptionAddOutOfBounds()
        {
          testList.add(gem1);
          testList.add(gem2);
          testList.add(gem3);

          assertThrows(IndexOutOfBoundsException.class, () -> {
            testList.add(4, gem3);
          });
        }
        @Test
        //Exception - adding at negative index
        void testExceptionAddNegativeIndex()
        {
          assertThrows(IndexOutOfBoundsException.class, () -> {
            testList.add(-1, gem3);
          });
        }

        @Test
          //Exception - get() from empty list should throw exception
        void testExceptionGetFromEmptyList()
        {
          assertThrows(IllegalStateException.class, () -> testList.get(0));
        }

        @Test
          //Exception - remove(element not in list) should throw exception
        void testExceptionRemoveNull()
        {
          testList.add(gem1);
          assertThrows(IllegalStateException.class, () -> testList.remove(gem3));
        }
  //S
        @Test
        //Side effect - set() should replace the element at the specified index
        void testSet()
        {
          testList.add(gem1);

          testList.set(0, gem2);
          assertEquals(gem2, testList.get(0));
        }

        @Test
        //Side effect - remove(index) should remove the element at the
          // specified index and shift the rest
        //this one fails

        void testRemove()
        {
          testList.add(gem1);
          testList.add(gem2);

          Gem removed = testList.remove(0);
          assertEquals(gem2, removed);
          assertEquals(1, testList.size());
          assertEquals(gem2, testList.get(0));
        }



}
