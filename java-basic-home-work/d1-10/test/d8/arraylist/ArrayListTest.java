package d8.arraylist;

import d8.List;
import d8.arraylist.ArrayList;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {

    private ArrayList createDataAndPrint(String[] data) {
        System.out.println("=============================");
        System.out.println("Prepared Data:");
        ArrayList<String> arrayList = new ArrayList<>(data);
        System.out.println(arrayList);
        System.out.println("-----------------------------");
        return arrayList;
    }

    //=================================================================================================================
    @Test
    public void testIterator() {
        //add
        ArrayList<String> list = new ArrayList<>();

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
        ArrayList<String> list = new ArrayList();

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
        ArrayList<String> list = new ArrayList();

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
    @Test(expected = InvalidParameterException.class)
    public void testAddToTheEndNegative() {
        //add
        ArrayList<String> list = new ArrayList();

        list.add(null);
    }
    //=================================================================================================================
    @Test
    public void testAddToTheEnd() {
        //add
        ArrayList<String> arrayList = new ArrayList();

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
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});

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
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        //=========================
        arrayList.add("C0", 20);
    }

    //=================================================================================================================
    private void testGetbyIdCore(ArrayList<String> arrayList, int lookupValue, String assertValue) throws IndexOutOfBoundsException {
        String val= arrayList.get(lookupValue);
        assertEquals(assertValue, String.valueOf(val));
        System.out.println(lookupValue + ":" + val);
    }

    @Test
    public void testGetbyId() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(arrayList, 4, "C");//middle
        testGetbyIdCore(arrayList, 0, "A0");//first
        testGetbyIdCore(arrayList, arrayList.size()-1, "F");//last
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByIdNegative() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(arrayList, 20, null);
    }

    //=================================================================================================================
    public void testRemoveByIdCore(ArrayList<String> arrayList, int index) {
        int sizeBefore = arrayList.size();
        System.out.println("Remove index:" + index);
        arrayList.remove(index);
        int sizeAfter = arrayList.size();
        assertEquals(sizeBefore, sizeAfter + 1);
    }

    @Test
    public void testRemoveById() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testRemoveByIdCore(arrayList, 0);//first
        testRemoveByIdCore(arrayList, arrayList.size()-1);//last
        testRemoveByIdCore(arrayList, 4);//middle
        System.out.println(arrayList);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByIdNegative() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        arrayList.remove(20);
    }

    @Test()
    public void testRemoveByIdEmptyList() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{});
        assertEquals(null,arrayList.remove(20));
    }

    //=================================================================================================================
    public void testIndexOfCore(ArrayList<String> arrayList,String lookupValue, int assertValue) {
        int index = arrayList.indexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index);
    }
    @Test
    public void testIndexOf() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C0", "C", "D", "E", "E1"});

        testIndexOfCore(arrayList,"A",0);//first
        testIndexOfCore(arrayList,"E1",arrayList.size()-1);//last
        testIndexOfCore(arrayList,"C",3);//midle

    }

    @Test
    public void testIndexOfNegative() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C0", "C", "D", "E", "E1"});
        testIndexOfCore(arrayList,"E2",-1);
    }

    //=================================================================================================================
    public void testLastIndexOfCore(ArrayList<String> arrayList,String lookupValue, int assertValue) {
        int index = arrayList.lastIndexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index);
    }
    @Test
    public void testLastIndexOf() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C0", "C", "D", "E", "E1"});

        testLastIndexOfCore(arrayList,"A",0);//first
        testLastIndexOfCore(arrayList,"E1",arrayList.size()-1);//last
        testLastIndexOfCore(arrayList,"B",1);//middle
    }

    @Test
    public void testLastIndexOfNegative() {
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});

        testLastIndexOfCore(arrayList,"M",-1);
    }
    //=================================================================================================================
    public void testSetCore(ArrayList<String> arrayList, String value, int index){

        String result = arrayList.set(value,index);
        assertEquals(value, result);

        String resultValue = arrayList.get(index);
        assertEquals(value,resultValue);
    };
    @Test
    public void testSet(){
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});

        testSetCore(arrayList,"C0",2);//middle
        testSetCore(arrayList,"A0",0);//first
        testSetCore(arrayList,"E1",arrayList.size()-1);//last

        System.out.println(arrayList);
    }
    //=================================================================================================================
    @Test
    public void testClear(){
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        assertEquals(true,arrayList.size()>0);

        arrayList.clear();
        assertEquals(0,arrayList.size());
        System.out.println(arrayList);
    }
    //=================================================================================================================
    @Test
    public void testIsEmpty(){
        ArrayList<String> arrayList = createDataAndPrint(new String[]{});
        assertEquals(true,arrayList.isEmpty());
    }
    @Test
    public void testIsEmptyNegative(){
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        assertEquals(false,arrayList.isEmpty());
    }
    //=================================================================================================================
    @Test
    public void testContains(){
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        assertEquals(true,arrayList.contains("A"));
        assertEquals(true,arrayList.contains("C"));
        assertEquals(true,arrayList.contains("E"));
    }
    //=================================================================================================================
    @Test
    public void testContainsNegative(){
        ArrayList<String> arrayList = createDataAndPrint(new String[]{"A", "B", "C", "D", "E"});
        assertEquals(false,arrayList.contains("A0"));
    }

}
