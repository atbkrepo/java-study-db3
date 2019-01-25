package d5.hashmap;

import d5.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashMap implements Map, Iterable {

    private static final int INITIAL_CAPASITY = 3;
    private int size;
    private static final double LOAD_FACTOR = 0.75;
    private static final double GROWTH_FACTOR = 2;

    private List<ArrayList<Entry>> buckets = new ArrayList<>(INITIAL_CAPASITY);

    public HashMap() {
        for (int i = 0; i < INITIAL_CAPASITY; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int getBucketId(Object o) {
        return o.hashCode() % INITIAL_CAPASITY;
    }

    private List<Entry> getEntryListByKey(Object key) {
        List<Entry> entryList = buckets.get(getBucketId(key));
        return entryList == null ? new ArrayList<>() : entryList;
    }


    @Override
    public Object put(Object key, Object value) {
        List<Entry> entryList = getEntryListByKey(key);

        for (Entry entry : entryList) {
            if (entry.key.equals(key)) {
                Object returnValue = entry.val;
                entry.val = value;
                return returnValue;
            }
        }
        entryList.add(new Entry(key, value));
        size++;
        return null;
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        List<Entry> entryList = getEntryListByKey(key);
        for (Entry entry : entryList) {
            if (entry.key.equals(key)) {
                return entry.val;
            }
        }
        entryList.add(new Entry(key, value));
        size++;
        return null;
    }

    @Override
    public Object get(Object key) {
        Entry entry = getEntry(key);
        return entry==null?null:entry.val;
    }

    public Entry getEntry(Object key) {
        List<Entry> entryList = getEntryListByKey(key);
        for (Entry entry : entryList) {
            if (entry.key.equals(key)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        List<Entry> entryList = getEntryListByKey(key);
        for (Iterator iterator = entryList.iterator(); iterator.hasNext(); ) {
            Entry e = (Entry) iterator.next();
            if (e.key.equals(key)) {
                Object returnValue = e.val;
                iterator.remove();
                size--;
                return returnValue;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        List<Entry> entryList = getEntryListByKey(key);
        for (Entry entry : entryList) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    //==================================================================================================================
    public class Entry {
        private Object key;
        private Object val;

        public Entry(Object key, Object val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }
    //==================================================================================================================
    @Override
    public Iterator iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator{
        private int pos;
        private int bucketPos;
        private int bucketIndex;

        @Override
        public boolean hasNext() {
            return pos<size();
        }

        @Override
        public Entry next() {
            if (!hasNext()){return null;}
            Entry res = null;

            List<Entry> entries = buckets.get(bucketPos);
            if (entries==null||entries.size()==0){
                bucketPos++;
                bucketIndex=0;
                res = next();
            }else{
               res  = entries.get(bucketIndex);
               if (entries.size()>bucketIndex+1){
                   bucketIndex++;
               }else{
                   bucketIndex=0;
                   bucketPos++;
               }
               pos++;
            }
            return res;
        }

        @Override
        public void remove() {
            if (size()==0){return;}

            List<Entry> entries = buckets.get(bucketPos);
            if (entries==null||entries.size()==0){
                bucketPos++;
                bucketIndex=0;
                remove();
            }else{
                entries.remove(bucketIndex);
                if (entries.size()<=bucketIndex){
                    bucketIndex=0;
                    bucketPos++;
                }
                size--;
            }
        }
    }


}
