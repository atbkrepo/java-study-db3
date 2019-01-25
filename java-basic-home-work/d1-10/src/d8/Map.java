package d8;

import d8.hashmap.HashMap;

public interface Map<K,V> extends Iterable<HashMap.Entry> {
    V put(K key, V value);

    V putIfAbsent(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    int size();

    void putAll(Map<K, V> map);

    void putAllIfAbsent(Map<K, V> map);
}