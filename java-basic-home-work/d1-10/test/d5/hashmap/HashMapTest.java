package d5.hashmap;

import d5.Map;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;

public class HashMapTest {

    private HashMap createAndFill(){
            Map map = new HashMap();
            map.put(1, "A");
            map.put(2, "B");
            map.put(3, "C");
            return (HashMap) map;
    }

    @Test
    public void testAddUniqueElements() {
        Map map = new HashMap();

        Object o1 = map.put(1, "A");
        assertEquals(null, o1);

        Object o2 = map.put(2, "B");
        assertEquals(null, o2);

        Object o3 = map.put(3, "C");
        assertEquals(null, o3);

        assertEquals(3, map.size());
    }

    @Test
    public void testAddNonUniqueElements() {
        Map map = createAndFill();
        assertEquals(3, map.size());


        Object o4 = map.put(2, "C");
        assertEquals("B", o4);

        Object o5 = map.put(4, "D");
        assertEquals(null, o5);


        assertEquals(4, map.size());
    }

    @Test
    public void testGetElements() {
        Map map = createAndFill();
        assertEquals(3, map.size());


        Object o4 = map.put(2, "C");
        assertEquals("B", o4);

        Object o5 = map.put(4, "D");
        assertEquals(null, o5);


        assertEquals(4, map.size());

        Object g1 = map.get(2);
        assertEquals("C", g1);

        //non exists element
        Object g2 = map.get(10);
        assertEquals(null, g2);
    }

    @Test
    public void testPutIfAbsentElement() {
        Map map = createAndFill();

        assertEquals(3, map.size());


        Object g1 = map.putIfAbsent(4,"D");
        assertEquals(null, g1);
        assertEquals(4, map.size());

        //non exists element
        Object g2 = map.putIfAbsent(2,"BB");
        assertEquals("B", g2);
        assertEquals(4, map.size());
    }

    @Test
    public void testRemoveElement() {
        Map map = createAndFill();
        assertEquals(3, map.size());


        Object g1 = map.remove(2);
        assertEquals("B", g1);
        assertEquals(2, map.size());

        //non exists element
        Object g2 = map.remove(10);
        assertEquals(null, g2);
        assertEquals(2, map.size());
    }

    @Test
    public void testContainsKey() {
        Map map = createAndFill();
        assertEquals(3, map.size());


        Object g1 = map.containsKey(2);
        assertEquals(true, g1);

        //non exists element
        Object g2 = map.containsKey(10);
        assertEquals(false, g2);
    }

    @Test
    public void testGetSize() {
        Map map = createAndFill();
        assertEquals(3, map.size());

    }

    @Test
    public void testIterator() {
        HashMap map = createAndFill();
        assertEquals(3, map.size());
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "E");
        map.put(7, "F");

        int i=0;
        for(Iterator iterator = map.iterator();iterator.hasNext();){
            System.out.println((HashMap.Entry)iterator.next());
            i++;
        }
        assertEquals(i, map.size());
    }

    @Test
    public void testIteratorLessThenCapacity() {
        HashMap map = createAndFill();
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
        HashMap map = createAndFill();
        assertEquals(3, map.size());
        map.put(4, "D");
        map.put(5, "E");
        map.put(6, "E");
        map.put(7, "F");

        for(Iterator iterator = map.iterator();iterator.hasNext();){
            iterator.remove();
        }

        assertEquals(0, map.size());
    }

    @Test
    public void testIteratorRemoveAllLessThenCapacity() {
        HashMap map = createAndFill();
        assertEquals(3, map.size());
        map.remove(1);
        assertEquals(2, map.size());

        for(Iterator iterator = map.iterator();iterator.hasNext();){
            iterator.remove();
        }

        assertEquals(0, map.size());
    }

}
