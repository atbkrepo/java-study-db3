package d4.DoubleLinkedList;

import d4.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoubleDoubleLinkedListTest {

    private List createDataAndPrint(Object[] data) {
        System.out.println("=============================");
        System.out.println("Prepared Data:");
        List list = new DoubleLinkedList();
        for (int i = 0; i <data.length ; i++) {
            list.add(data[i]);
        }
        System.out.println(list);
        System.out.println("-----------------------------");
        return list;
    }

    //=================================================================================================================
    @Test
    public void testAsCreate() {
        //add
        List list = new DoubleLinkedList("A");

        System.out.println(list);
        assertEquals(1, list.size());
    }

    //=================================================================================================================
    @Test
    public void testAddToTheEndByZeroPos() {
        //add
        List list = new DoubleLinkedList();

        list.add("A",0);

        System.out.println(list);
        assertEquals(1, list.size());
    }

    //=================================================================================================================
    @Test
    public void testAddToTheEnd() {
        //add
        List list = new DoubleLinkedList();

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
        List list = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

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
        List arrayList = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        //=========================
        arrayList.add("C0", 20);
    }

    //=================================================================================================================
    private void testGetbyIdCore(List list, int lookupValue, String assertValue) throws IndexOutOfBoundsException {
        Object val= list.get(lookupValue);
        assertEquals(assertValue, String.valueOf(val));
        System.out.println(lookupValue + ":" + val);
    }

    @Test
    public void testGetbyId() {
        List list = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(list, 4, "C");//middle
        testGetbyIdCore(list, 6, "E0");//middle
        testGetbyIdCore(list, 0, "A0");//first
        testGetbyIdCore(list, list.size()-1, "F");//last
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByIdNegative() {
        List list = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testGetbyIdCore(list, 20, null);
    }

    //=================================================================================================================
    public void testRemoveByIdCore(List list, int index, Object removedValue) {
        int sizeBefore = list.size();
        System.out.println("Remove index:" + index);
        assertEquals(removedValue,list.remove(index));

        int sizeAfter = list.size();
        assertEquals(sizeBefore, sizeAfter + 1);
    }

    @Test
    public void testRemoveById() {
        List list = createDataAndPrint(new Object[]{"A0", "A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        testRemoveByIdCore(list, 0,"A0");//first
        testRemoveByIdCore(list, list.size()-1, "F");//last
        testRemoveByIdCore(list, 4,"D");//middle
        System.out.println(list);
    }

    @Test
    public void testRemoveByIdNegative() {
        List list = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E0", "E", "E1", "F"});
        int sizeBefore = list.size();
        assertEquals(null,list.remove(20));
        assertEquals(sizeBefore,list.size());
    }

    @Test()
    public void testRemoveByIdEmptyList() {
        List list = createDataAndPrint(new Object[]{});
        assertEquals(null,list.remove(1));
    }

    //=================================================================================================================
    public void testIndexOfCore(List list,Object lookupValue, int assertValue) {
        int index = list.indexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index+": for ("+lookupValue+")");
    }
    @Test
    public void testIndexOf() {
        List list = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "E1"});

        testIndexOfCore(list,"A",0);//first
        testIndexOfCore(list,"E1",list.size()-1);//last
        testIndexOfCore(list,"C",3);//midle

    }

    @Test
    public void testIndexOfNegative() {
        List list = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "E1"});
        testIndexOfCore(list,"E2",-1);
    }

    //=================================================================================================================
    public void testLastIndexOfCore(List list,Object lookupValue, int assertValue) {
        int index = list.lastIndexOf(lookupValue);
        assertEquals(assertValue, index);
        System.out.println(assertValue + ":" + index+": for ("+lookupValue+")");
    }
    @Test
    public void testLastIndexOf() {
        List list = createDataAndPrint(new Object[]{"A", "B", "C0", "C", "D", "E", "C", "E1"});

        testLastIndexOfCore(list,"A",0);//first
        testLastIndexOfCore(list,"E1",list.size()-1);//last
        testLastIndexOfCore(list,"B",1);//middle

        testLastIndexOfCore(list,"C",6);//2nd param from start first from the end
    }

    @Test
    public void testLastIndexOfNegative() {
        List list = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

        testLastIndexOfCore(list,"M",-1);
    }
  //=================================================================================================================
    public void testSetCore(List list, Object value, int index){

        Object result = list.set(value,index);
        assertEquals(value, result);

        Object resultValue = list.get(index);
        assertEquals(value,resultValue);
    };
    @Test
    public void testSet(){
        List list = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});

        testSetCore(list,"C0",2);//middle
        testSetCore(list,"A0",0);//first
        testSetCore(list,"E1",list.size()-1);//last

        System.out.println(list);
    }
    //=================================================================================================================
    @Test
    public void testClear(){
        List list = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(true,list.size()>0);

        list.clear();
        assertEquals(0,list.size());
        System.out.println(list);
    }
      //=================================================================================================================
    @Test
    public void testIsEmpty(){
        List list = createDataAndPrint(new Object[]{});
        assertEquals(true,list.isEmpty());
    }
    @Test
    public void testIsEmptyNegative(){
        List list = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(false,list.isEmpty());
    }
    //=================================================================================================================
   @Test
    public void testContains(){
        List list = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(true,list.contains("A"));
        assertEquals(true,list.contains("C"));
        assertEquals(true,list.contains("E"));
    }
    //=================================================================================================================
    @Test
    public void testContainsNegative(){
        List list = createDataAndPrint(new Object[]{"A", "B", "C", "D", "E"});
        assertEquals(false,list.contains("A0"));
    }


}
