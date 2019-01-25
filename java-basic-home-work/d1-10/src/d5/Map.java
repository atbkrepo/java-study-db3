package d5;

public interface Map {
    Object put(Object key, Object value);

    Object putIfAbsent(Object key, Object value);

    Object get(Object key);

    Object remove(Object key);

    boolean containsKey(Object key);

    int size();
}