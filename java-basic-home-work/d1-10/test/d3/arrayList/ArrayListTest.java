package d3.arrayList;

import org.junit.Test;

import java.security.InvalidParameterException;

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
    @Test(expected = InvalidParameterException.class)
    public void testAddToTheEndNegative() {
        //add
        ArrayList arrayList = new ArrayList();

        arrayList.add(null);
    }
    //=================================================================================================================
    @Test
    public void testAddToTheEnd() {
        //add
        ArrayList arrayList = new ArrayList();

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
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

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
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        //=========================
        arrayList.add("C0", 20);
    }

    //=================================================================================================================
    private void testGetbyIdCore(ArrayList arrayList, int lookupValue, String assertValue) throws IndexOutOfBoundsException {
        Object val= arrayList.get(lookupValue);
        assertEquals(assertValue, String.valueOf(val));
        System.out.println(lookupValue + ":" + val);
    }

    @Test
    public void testGetbyId() {
        ArrayList arrayList = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(arrayList, 4, "C");//middle
        testGetbyIdCore(arrayList, 0, "A0");//first
        testGetbyIdCore(arrayList, arrayList.size()-1, "F");//last
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByIdNegative() {
        ArrayList arrayList = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(arrayList, 20, null);
    }

    //=================================================================================================================
    public void testRemoveByIdCore(ArrayList arrayList, int index) {
        int sizeBefore = arrayList.size();
        System.out.println("Remove index:" + index);
        arrayList.remove(index);
        int sizeAfter = arrayList.size();
        assertEquals(sizeBefore, sizeAfter + 1);
    }

    @Test
    public void testRemoveById() {
        ArrayList arrayList = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testRemoveByIdCore(arrayList, 0);//first
        testRemoveByIdCore(arrayList, arrayList.size()-1);//last
        testRemoveByIdCore(arrayList, 4);//middle
        System.out.println(arrayList);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByIdNegative() {
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        arrayList.remove(20);
    }

    @Test()
    public void testRemoveByIdEmptyList() {
        ArrayList arrayList = createDataAndPrint(new Object[]{});
        assertEquals(false,arrayList.remove(20));
    }

    //=================================================================================================================
    public void testIndexOfCore(ArrayList arrayList,Object lookupValue, int assertValue) {
        int index = arrayList.indexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index);
    }
    @Test
    public void testIndexOf() {
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "E1"});

        testIndexOfCore(arrayList,"A",0);//first
        testIndexOfCore(arrayList,"E1",arrayList.size()-1);//last
        testIndexOfCore(arrayList,"C",3);//midle

    }

    @Test
    public void testIndexOfNegative() {
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "E1"});
        testIndexOfCore(arrayList,"E2",-1);
    }

    //=================================================================================================================
    public void testLastIndexOfCore(ArrayList arrayList,Object lookupValue, int assertValue) {
        int index = arrayList.lastIndexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index);
    }
    @Test
    public void testLastIndexOf() {
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "E1"});

        testLastIndexOfCore(arrayList,"A",0);//first
        testLastIndexOfCore(arrayList,"E1",arrayList.size()-1);//last
        testLastIndexOfCore(arrayList,"B",1);//middle
    }

    @Test
    public void testLastIndexOfNegative() {
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

        testLastIndexOfCore(arrayList,"M",-1);
    }
    //=================================================================================================================
    public void testSetCore(ArrayList arrayList, Object value, int index){

        boolean result = arrayList.set(value,index);
        assertEquals(true, result);

        Object resultValue = arrayList.get(index);
        assertEquals(value,resultValue);
    };
    @Test
    public void testSet(){
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

        testSetCore(arrayList,"C0",2);//middle
        testSetCore(arrayList,"A0",0);//first
        testSetCore(arrayList,"E1",arrayList.size()-1);//last

        System.out.println(arrayList);
    }
    //=================================================================================================================
    @Test
    public void testClear(){
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(true,arrayList.size()>0);

        arrayList.clear();
        assertEquals(0,arrayList.size());
        System.out.println(arrayList);
    }
    //=================================================================================================================
    @Test
    public void testIsEmpty(){
        ArrayList arrayList = createDataAndPrint(new Object[]{});
        assertEquals(true,arrayList.isEmpty());
    }
    @Test
    public void testIsEmptyNegative(){
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(false,arrayList.isEmpty());
    }
    //=================================================================================================================
    @Test
    public void testContains(){
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(true,arrayList.contains("A"));
        assertEquals(true,arrayList.contains("C"));
        assertEquals(true,arrayList.contains("E"));
    }
    //=================================================================================================================
    @Test
    public void testContainsNegative(){
        ArrayList arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(false,arrayList.contains("A0"));
    }

}
