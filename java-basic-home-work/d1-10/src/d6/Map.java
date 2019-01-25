package d6;

public interface Map extends Iterable {
    Object put(Object key, Object value);

    Object putIfAbsent(Object key, Object value);

    Object get(Object key);

    Object remove(Object key);

    boolean containsKey(Object key);

    int size();

    void putAll(Map map);

    void putAllIfAbsent(Map map);
}