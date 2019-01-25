package d8.hashmap;

import d8.Map;
import d8.hashmap.HashMap;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;

public class HashMapTest {

    private  HashMap<Integer,String> createAndFill(){
        HashMap<Integer,String> map = new HashMap<>();
            map.put(1, "A");
            map.put(2, "B");
            map.put(3, "C");
            return (HashMap) map;
    }

    @Test
    public void testAddUniqueElements() {
        HashMap<Integer,String> map = new HashMap<>();

        String o1 = map.put(1, "A");
        assertEquals(null, o1);

        String o2 = map.put(2, "B");
        assertEquals(null, o2);

        String o3 = map.put(3, "C");
        assertEquals(null, o3);

        assertEquals(3, map.size());
    }

    @Test
    public void testAddNonUniqueElements() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());


        String o4 = map.put(2, "C");
        assertEquals("B", o4);

        String o5 = map.put(4, "D");
        assertEquals(null, o5);


        assertEquals(4, map.size());
    }

    @Test
    public void testGetElements() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());


        String o4 = map.put(2, "C");
        assertEquals("B", o4);

        String o5 = map.put(4, "D");
        assertEquals(null, o5);


        assertEquals(4, map.size());

        String g1 = map.get(2);
        assertEquals("C", g1);

        //non exists element
        String g2 = map.get(10);
        assertEquals(null, g2);
    }

    @Test
    public void testPutIfAbsentElement() {
        HashMap<Integer,String> map = createAndFill();

        assertEquals(3, map.size());


        String g1 = map.putIfAbsent(4,"D");
        assertEquals(null, g1);
        assertEquals(4, map.size());

        //non exists element
        String g2 = map.putIfAbsent(2,"BB");
        assertEquals("B", g2);
        assertEquals(4, map.size());
    }

    @Test
    public void testRemoveElement() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());


        String g1 = map.remove(2);
        assertEquals("B", g1);
        assertEquals(2, map.size());

        //non exists element
        String g2 = map.remove(10);
        assertEquals(null, g2);
        assertEquals(2, map.size());
    }

    @Test
    public void testContainsKey() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());


        boolean g1 = map.containsKey(2);
        assertEquals(true, g1);

        //non exists element
        boolean g2 = map.containsKey(10);
        assertEquals(false, g2);
    }

    @Test
    public void testGetSize() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());

    }

    @Test
    public void testIterator() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "F");
        map.put(7, "G");

        int i=0;
        for(Iterator iterator = map.iterator();iterator.hasNext();){
            System.out.println((HashMap.Entry)iterator.next());
            i++;
        }
        assertEquals(i, map.size());
    }

    @Test
    public void testIteratorLessThenCapacity() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());
        map.remove(1);
        assertEquals(2, map.size());

        int i=0;
        for(Iterator iterator = map.iterator();iterator.hasNext();){
            System.out.println((HashMap.Entry)iterator.next());
            i++;
        }
        assertEquals(i, map.size());
    }

    @Test
    public void testIteratorRemoveAll() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "F");
        map.put(7, "G");

        for(Iterator iterator = map.iterator();iterator.hasNext();){
            iterator.next();
            iterator.remove();
        }

        assertEquals(0, map.size());
    }

    @Test
    public void testIteratorRemoveAllLessThenCapacity() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());
        map.remove(1);
        assertEquals(2, map.size());

        for(Iterator iterator = map.iterator();iterator.hasNext();){
            iterator.next();
            iterator.remove();
        }

        assertEquals(0, map.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveAllLessThenCapacityNegative() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());
        map.remove(1);
        assertEquals(2, map.size());

        for(Iterator iterator = map.iterator();iterator.hasNext();){
            iterator.remove();
        }

        assertEquals(0, map.size());
    }

    @Test
    public void testPutAll() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());

        HashMap<Integer,String> map2 = new HashMap<>();
        map2.put(4,"D");
        map2.put(1,"AA");

        map.putAll(map2);

        assertEquals(4, map.size());

        assertEquals(map.get(4), "D");
        assertEquals(map.get(1), "AA");
    }

    @Test
    public void testPutAllIfAbsent() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());

        HashMap<Integer,String> map2 = new HashMap<>();
        map2.put(4,"D");
        map2.put(1,"AA");

        map.putAllIfAbsent(map2);

        assertEquals(4, map.size());

        assertEquals(map.get(4), "D");
        assertEquals(map.get(1), "A");
    }

    @Test
    public void testRebalance() {
        HashMap<Integer,String> map = createAndFill();
        assertEquals(3, map.size());
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "F");
        map.put(7, "G");


        int i=0;
        for(Iterator iterator = map.iterator();iterator.hasNext();){
            System.out.println((HashMap.Entry)iterator.next());
            i++;
        }
        assertEquals(i, map.size());
    }

}
