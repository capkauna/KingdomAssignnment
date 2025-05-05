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

        @Test
        //zero - constructor parameter shoud not break instantiation
        void testConstructorWithNullParam() {
        MyArrayList<Gem> list = new MyArrayList<Gem>(null);
        assertNotNull(list);
        assertTrue(list.isEmpty());
        }

        @Test
          //Zero - null handling - can add null elements to the list
        void testAddNull() {
          testList.add(null);
          assertEquals(1, testList.size());
          assertNull(testList.get(0));
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

        @Test
        // Many - add many elements (capacity check) - default capacity is 100
        // adding more should expand the list size
        public void testAddManyElements() {
        for (int i = 0; i < 200; i++) {
        testList.add(new GenericGem(GemType.GOLDNUGGET));
        }
        assertEquals(200, testList.size());
        assertEquals(GemType.GOLDNUGGET, testList.get(100).getGemType());
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
          assertEquals(3, testList.size());
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

        @Test
        // Interface - contains returns true if the list contains the specified element
        void testContainsElement() {
        testList.add(gem1);
        assertTrue(testList.contains(gem1));
        assertFalse(testList.contains(gem2));
        }

        @Test
        //Interface - get(element) should return the element at the specified index
        void testGetElement()
        {
          testList.add(gem1);
          testList.add(gem2);

          assertEquals(gem1, testList.get(0));
          assertEquals(gem2, testList.get(1));
        }

        @Test
        //Interface - get(index) should return the element at the specified index
        void testGetIndex()
        {
          testList.add(gem1);
          testList.add(gem2);

          assertEquals(gem1, testList.get(0));
          assertEquals(gem2, testList.get(1));
        }

        @Test
        //Interface - remove(element) should remove the specified element
        void testRemoveElement()
        {
          testList.add(gem1);
          testList.add(gem2);

          assertEquals(gem1, testList.remove(gem1));
          assertEquals(1, testList.size());
          assertEquals(gem2, testList.get(0));
        }

        @Test
        //Interface - isEmpty() should return true if the list is empty
        void testIsEmpty()
        {
          assertTrue(testList.isEmpty());
          testList.add(gem1);
          assertFalse(testList.isEmpty());
        }

        @Test
          //Interface - toString format should format list properly
        void testToStringFormat() {
          testList.add(gem1);
          testList.add(gem2);
          String output = testList.toString();
          assertTrue(output.startsWith("{") && output.endsWith("}"));
          assertTrue(output.contains(","));
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
          assertEquals(1, testList.size());
        }

        @Test
        //Side effect - remove(index) should remove the element at the
          // specified index and shift the rest

        void testRemove()
        {
          testList.add(gem1);
          testList.add(gem2);

          Gem removed = testList.remove(0);
          assertEquals(gem1, removed);
          assertEquals(1, testList.size());
          assertEquals(gem2, testList.get(0));
        }

        @Test
        //Side Effect (Unwanted) - isFull() should return false on an empty list
        //this test fails because this method is set to return true no matter what
        void testIsFullOnEmptyList()
        {
          assertEquals(0, testList.size());
          assertFalse(testList.isFull());
        }

        @Test
         //Interface - isFull() should return true only if the list is full
        void testIsFullOnFullList()
        {
          for (int i = 0; i < 100; i++)
          {
            testList.add(new GenericGem(GemType.AMETHYST));
          }
          assertTrue(testList.isFull());
        }








}
