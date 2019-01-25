package d5.arraylist;

import d5.List;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {

    private ArrayList createDataAndPrint(Object[] data) {
        System.out.println("=============================");
        System.out.println("Prepared Data:");
        ArrayList arrayList = new ArrayList(data);
        System.out.println(arrayList);
        System.out.println("-----------------------------");
        return arrayList;
    }

    //=================================================================================================================
    @Test
    public void testIterator() {
        //add
        List list = new ArrayList();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        String s = "";
        Iterator iter = ((ArrayList) list).iterator();
        while (iter.hasNext()) {
            s += iter.next();
        }
        assertEquals("ABCDE", s);

    }

    //=================================================================================================================
    @Test
    public void testIteratorRemove() {
        //add
        List list = new ArrayList();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        String s = "";
        Iterator iter = ((ArrayList) list).iterator();
        while (iter.hasNext()) {
            iter.remove();
        }
        assertEquals(0, list.size());

    }

    //=================================================================================================================
    @Test
    public void testIteratorRemoveOne() {
        //add
        List list = new ArrayList();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        int i=0;
        String s = "";
        Iterator iter = ((ArrayList) list).iterator();
        while (iter.hasNext()) {
            if (++i == 3) {
                iter.remove();
            }else {
                s += iter.next();
            }

        }
        assertEquals("ABDE", s);
        assertEquals(4, list.size());

    }
    //=================================================================================================================
    @Test(expected = InvalidParameterException.class)
    public void testAddToTheEndNegative() {
        //add
        List arrayList = new ArrayList();

        arrayList.add(null);
    }
    //=================================================================================================================
    @Test
    public void testAddToTheEnd() {
        //add
        List arrayList = new ArrayList();

        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("E");

        System.out.println(arrayList);
        assertEquals(5, arrayList.size());
    }

    //=================================================================================================================
    @Test
    public void testAddInside() {
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

        arrayList.add("C0", 2);
        arrayList.add("A0", 0);
        arrayList.add("E0", 6);
        arrayList.add("E1", 8);
        assertEquals(9, arrayList.size());

        //add
        arrayList.add("F");
        assertEquals(10, arrayList.size());

        System.out.println(arrayList);
    }

    //=================================================================================================================
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddInsideNegative() {
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        //=========================
        arrayList.add("C0", 20);
    }

    //=================================================================================================================
    private void testGetbyIdCore(List arrayList, int lookupValue, String assertValue) throws IndexOutOfBoundsException {
        Object val= arrayList.get(lookupValue);
        assertEquals(assertValue, String.valueOf(val));
        System.out.println(lookupValue + ":" + val);
    }

    @Test
    public void testGetbyId() {
        List arrayList = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(arrayList, 4, "C");//middle
        testGetbyIdCore(arrayList, 0, "A0");//first
        testGetbyIdCore(arrayList, arrayList.size()-1, "F");//last
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByIdNegative() {
        List arrayList = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(arrayList, 20, null);
    }

    //=================================================================================================================
    public void testRemoveByIdCore(List arrayList, int index) {
        int sizeBefore = arrayList.size();
        System.out.println("Remove index:" + index);
        arrayList.remove(index);
        int sizeAfter = arrayList.size();
        assertEquals(sizeBefore, sizeAfter + 1);
    }

    @Test
    public void testRemoveById() {
        List arrayList = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testRemoveByIdCore(arrayList, 0);//first
        testRemoveByIdCore(arrayList, arrayList.size()-1);//last
        testRemoveByIdCore(arrayList, 4);//middle
        System.out.println(arrayList);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByIdNegative() {
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        arrayList.remove(20);
    }

    @Test()
    public void testRemoveByIdEmptyList() {
        List arrayList = createDataAndPrint(new Object[]{});
        assertEquals(null,arrayList.remove(20));
    }

    //=================================================================================================================
    public void testIndexOfCore(List arrayList,Object lookupValue, int assertValue) {
        int index = arrayList.indexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index);
    }
    @Test
    public void testIndexOf() {
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "E1"});

        testIndexOfCore(arrayList,"A",0);//first
        testIndexOfCore(arrayList,"E1",arrayList.size()-1);//last
        testIndexOfCore(arrayList,"C",3);//midle

    }

    @Test
    public void testIndexOfNegative() {
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "E1"});
        testIndexOfCore(arrayList,"E2",-1);
    }

    //=================================================================================================================
    public void testLastIndexOfCore(List arrayList,Object lookupValue, int assertValue) {
        int index = arrayList.lastIndexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index);
    }
    @Test
    public void testLastIndexOf() {
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "E1"});

        testLastIndexOfCore(arrayList,"A",0);//first
        testLastIndexOfCore(arrayList,"E1",arrayList.size()-1);//last
        testLastIndexOfCore(arrayList,"B",1);//middle
    }

    @Test
    public void testLastIndexOfNegative() {
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

        testLastIndexOfCore(arrayList,"M",-1);
    }
    //=================================================================================================================
    public void testSetCore(List arrayList, Object value, int index){

        Object result = arrayList.set(value,index);
        assertEquals(value, result);

        Object resultValue = arrayList.get(index);
        assertEquals(value,resultValue);
    };
    @Test
    public void testSet(){
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

        testSetCore(arrayList,"C0",2);//middle
        testSetCore(arrayList,"A0",0);//first
        testSetCore(arrayList,"E1",arrayList.size()-1);//last

        System.out.println(arrayList);
    }
    //=================================================================================================================
    @Test
    public void testClear(){
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(true,arrayList.size()>0);

        arrayList.clear();
        assertEquals(0,arrayList.size());
        System.out.println(arrayList);
    }
    //=================================================================================================================
    @Test
    public void testIsEmpty(){
        List arrayList = createDataAndPrint(new Object[]{});
        assertEquals(true,arrayList.isEmpty());
    }
    @Test
    public void testIsEmptyNegative(){
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(false,arrayList.isEmpty());
    }
    //=================================================================================================================
    @Test
    public void testContains(){
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(true,arrayList.contains("A"));
        assertEquals(true,arrayList.contains("C"));
        assertEquals(true,arrayList.contains("E"));
    }
    //=================================================================================================================
    @Test
    public void testContainsNegative(){
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(false,arrayList.contains("A0"));
    }

}
