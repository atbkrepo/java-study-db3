package d8.linkedlist;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class LinkedListTest {

    private LinkedList<String> createDataAndPrint(String[] data) {
        System.out.println("=============================");
        System.out.println("Prepared Data:");
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
        System.out.println(list);
        System.out.println("-----------------------------");
        return list;
    }

    //=================================================================================================================
    @Test
    public void testIterator() {
        //add
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        String s = "";
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            s += iter.next();
        }
        assertEquals("ABCDE", s);

    }

    //=================================================================================================================
    @Test
    public void testIteratorRemove() {
        //add
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        String s = "";
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        assertEquals(0, list.size());

    }

    //=================================================================================================================
    @Test
    public void testIteratorRemoveOne() {
        //add
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        int i=0;
        String s = "";
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            if (++i == 3) {
                iter.next();
                iter.remove();
            }else {
                s += iter.next();
            }

        }
        assertEquals("ABDE", s);
        assertEquals(4, list.size());

    }
    //=================================================================================================================
    @Test
    public void testIteratorRemoveLast() {
        //add
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        int i=0;
        String s = "";
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            if (++i == 5) {
                iter.next();
                iter.remove();
            }else {
                s += iter.next();
            }

        }
        assertEquals("ABCD", s);
        assertEquals(4, list.size());

    }

    //=================================================================================================================
    @Test
    public void testAsCreate() {
        //add
        LinkedList<String> list = new LinkedList<>("A");

        System.out.println(list);
        assertEquals(1, list.size());
    }

    //=================================================================================================================
    @Test
    public void testAddToTheEndByZeroPos() {
        //add
        LinkedList<String> list = new LinkedList<>();

        list.add("A", 0);

        System.out.println(list);
        assertEquals(1, list.size());
    }

    //=================================================================================================================
    @Test
    public void testAddToTheEnd() {
        //add
        LinkedList<String> list = new LinkedList<>();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        System.out.println(list);
        assertEquals(5, list.size());
    }

    //=================================================================================================================
    @Test
    public void testAddInside() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});

        list.add("C0", 2);
        list.add("A0", 0);
        list.add("E0", 6);
        list.add("E1", 8);
        System.out.println(list);
        assertEquals(9, list.size());

        //add
        list.add("F");
        System.out.println(list);
        assertEquals(10, list.size());

        list.add("F1", 10);
        System.out.println(list);
        assertEquals(11, list.size());


    }


    //=================================================================================================================
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddInsideNegative() {
        LinkedList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        //=========================
        arrayList.add("C0", 20);
    }

    //=================================================================================================================
    private void testGetbyIdCore(LinkedList<String> list, int lookupValue, String assertValue) throws IndexOutOfBoundsException {
        String val = list.get(lookupValue);
        assertEquals(assertValue, String.valueOf(val));
        System.out.println(lookupValue + ":" + val);
    }

    @Test
    public void testGetbyId() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(list, 4, "C");//middle
        testGetbyIdCore(list, 0, "A0");//first
        testGetbyIdCore(list, list.size() - 1, "F");//last
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByIdNegative() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(list, 20, null);
    }

    //=================================================================================================================
    public void testRemoveByIdCore(LinkedList<String> list, int index, String removedValue) {
        int sizeBefore = list.size();
        System.out.println("Remove index:" + index);
        assertEquals(removedValue, list.remove(index));

        int sizeAfter = list.size();
        assertEquals(sizeBefore, sizeAfter + 1);
    }

    @Test
    public void testRemoveById() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testRemoveByIdCore(list, 0, "A0");//first
        testRemoveByIdCore(list, list.size() - 1, "F");//last
        testRemoveByIdCore(list, 4, "D");//middle
        System.out.println(list);
    }

    @Test
    public void testRemoveByIdNegative() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        int sizeBefore = list.size();
        assertEquals(null, list.remove(20));
        assertEquals(sizeBefore, list.size());
    }

    @Test()
    public void testRemoveByIdEmptyList() {
        LinkedList<String> list = createDataAndPrint(new String[]{});
        assertEquals(null, list.remove(1));
    }

    //=================================================================================================================
    public void testIndexOfCore(LinkedList<String> list, String lookupValue, int assertValue) {
        int index = list.indexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index + ": for (" + lookupValue + ")");
    }

    @Test
    public void testIndexOf() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C0", "C", "D", "E", "E1"});

        testIndexOfCore(list, "A", 0);//first
        testIndexOfCore(list, "E1", list.size() - 1);//last
        testIndexOfCore(list, "C", 3);//midle

    }

    @Test
    public void testIndexOfNegative() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C0", "C", "D", "E", "E1"});
        testIndexOfCore(list, "E2", -1);
    }

    //=================================================================================================================
    public void testLastIndexOfCore(LinkedList<String> list, String lookupValue, int assertValue) {
        int index = list.lastIndexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index + ": for (" + lookupValue + ")");
    }

    @Test
    public void testLastIndexOf() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C0", "C", "D", "E", "C", "E1"});

        testLastIndexOfCore(list, "A", 0);//first
        testLastIndexOfCore(list, "E1", list.size() - 1);//last
        testLastIndexOfCore(list, "B", 1);//middle

        testLastIndexOfCore(list, "C", 6);//2nd param from start first from the end
    }

    @Test
    public void testLastIndexOfNegative() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});

        testLastIndexOfCore(list, "M", -1);
    }

    //=================================================================================================================
    public void testSetCore(LinkedList<String> list, String value, int index) {

        String result = list.set(value, index);
        assertEquals(value, result);

        String resultValue = list.get(index);
        assertEquals(value, resultValue);
    }

    ;

    @Test
    public void testSet() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});

        testSetCore(list, "C0", 2);//middle
        testSetCore(list, "A0", 0);//first
        testSetCore(list, "E1", list.size() - 1);//last

        System.out.println(list);
    }

    //=================================================================================================================
    @Test
    public void testClear() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        assertEquals(true, list.size() > 0);

        list.clear();
        assertEquals(0, list.size());
        System.out.println(list);
    }

    //=================================================================================================================
    @Test
    public void testIsEmpty() {
        LinkedList<String> list = createDataAndPrint(new String[]{});
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testIsEmptyNegative() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        assertEquals(false, list.isEmpty());
    }

    //=================================================================================================================
    @Test
    public void testContains() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        assertEquals(true, list.contains("A"));
        assertEquals(true, list.contains("C"));
        assertEquals(true, list.contains("E"));
    }

    //=================================================================================================================
    @Test
    public void testContainsNegative() {
        LinkedList<String> list = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        assertEquals(false, list.contains("A0"));
    }


}
