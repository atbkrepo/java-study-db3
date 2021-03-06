package d8.hashmap;

import d8.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashMap<K,V> implements Map<K,V> {

    private static final int INITIAL_CAPASITY = 3;
    private int capacity = INITIAL_CAPASITY;
    private int size;
    private static final double LOAD_FACTOR = 0.75;
    private static final double GROWTH_FACTOR = 2;

    private List<ArrayList<Entry>> buckets = new ArrayList<>(INITIAL_CAPASITY);

    private void rebalance() {
        if (size > capacity * LOAD_FACTOR) {
            int oldCapacity = capacity;
            for (int i = capacity; i < capacity * GROWTH_FACTOR; i++) {
                buckets.add(new ArrayList<>());
            }
            capacity = (int)(capacity*GROWTH_FACTOR);
            for (int i = 0; i < oldCapacity; i++) {
                ArrayList<Entry> entries = buckets.get(i);
                for(int j=0; j<entries.size();j++){
                    Entry entry = entries.get(j);
                    if (getBucketId(entry.key)!=i){
                        entries.remove(j);
                        size--;
                        put(entry.key,entry.val);
                    }
                }
            }

        }
    }

    public HashMap() {
        for (int i = 0; i < INITIAL_CAPASITY; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int getBucketId(K key) {
        return key.hashCode() % capacity;
    }

    private List<Entry> getEntryListByKey(K key) {
        List<Entry> entryList = buckets.get(getBucketId(key));
        return entryList == null ? new ArrayList<>() : entryList;
    }


    @Override
    public V put(K key, V value) {
        List<Entry> entryList = getEntryListByKey(key);

        for (Entry entry : entryList) {
            if (entry.key.equals(key)) {
                V returnValue = entry.val;
                entry.val = value;
                return returnValue;
            }
        }
        entryList.add(new Entry(key, value));
        size++;
        rebalance();
        return null;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        List<Entry> entryList = getEntryListByKey(key);
        for (Entry entry : entryList) {
            if (entry.key.equals(key)) {
                return entry.val;
            }
        }
        entryList.add(new Entry(key, value));
        size++;
        rebalance();
        return null;
    }

    @Override
    public void putAll(Map<K,V> map) {
        for (Entry entry : map) {
            put(entry.key, entry.val);
        }
    }

    @Override
    public void putAllIfAbsent(Map<K,V> map) {
        for (Entry entry : map) {
            putIfAbsent(entry.key, entry.val);
        }
    }

    @Override
    public V get(K key) {
        Entry entry = getEntry(key);
        return entry == null ? null : entry.val;
    }

    public Entry getEntry(K key) {
        List<Entry> entryList = getEntryListByKey(key);
        for (Entry entry : entryList) {
            if (entry.key.equals(key)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        List<Entry> entryList = getEntryListByKey(key);
        for (Iterator<Entry> iterator = entryList.iterator(); iterator.hasNext(); ) {
            Entry e = iterator.next();
            if (e.key.equals(key)) {
                V returnValue = e.val;
                iterator.remove();
                size--;
                return returnValue;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
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
        private K key;
        private V val;

        public Entry(K key, V val) {
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

    private class MyHashMapIterator implements Iterator {
        private int pos;
        private int bucketPos;
        private int bucketIndex;
        private boolean isNext = false;

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public Entry next() {
            if (!hasNext()) {
                return null;
            }
            Entry res = null;

            List<Entry> entries = buckets.get(bucketPos);
            if (entries.size()<bucketIndex+1) {
                bucketPos++;
                bucketIndex = 0;
                res = next();
            } else {
                bucketIndex++;
                pos++;
                res = entries.get(bucketIndex-1);
                isNext = true;
            }
            return res;
        }

        @Override
        public void remove() {
            if (size() == 0 || !isNext) {
                throw new IllegalStateException();
            }

            List<Entry> entries = buckets.get(bucketPos);
            entries.remove(bucketIndex-1);
            bucketIndex--;
            size--;
            pos--;
        }
    }


}
