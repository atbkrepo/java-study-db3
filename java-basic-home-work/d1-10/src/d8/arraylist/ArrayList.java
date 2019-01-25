package d8.arraylist;

import d8.List;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements List<T>, Iterable {

    private T[] array;
    private int size;

    public ArrayList() {
        this(4);
    }

    public ArrayList(T[] array) {
        this.array = array;
        this.size = array.length;
    }

    public ArrayList(int initialSize) {
        size = 0;
        array = (T[]) new Object[initialSize];
    }

    private void extend() {
        if (array[array.length - 1] != null) {
            T[] newArray = (T[]) new Object[(int) (size * 1.5)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndexParam(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + (size - 1));
        }
    }

    private void checkValueParam(T value) {
        if (value == null) {
            throw new InvalidParameterException("Value can't be null");
        }
    }

    /**
     * add value to the end of the list
     *
     * @param value*/
    @Override
    public void add(T value) {
        add(value, size);
    }
    /**
     * we can add value by index between [0, size]
     * otherwise throw new IndexOutOfBoundsException
     * [A, B, C] . add("D", [0, 1, 2, 3])
     **/

    @Override
    public void add(T value, int index) {
        checkValueParam(value);
        checkIndexParam(index);
        extend();

        if (index == size) {//last
            array[size++] = value;
        } else if (index == 0) {//first
            System.arraycopy(array, 0, array, 1, size);
            array[0] = value;
            size++;
        } else {//middle
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    /**
     * [A, B, C] remove = 0
     * [B (index = 0) , C (index = 1)]
     */
    @Override
    public T remove(int index) {
        if (size == 0) {
            return null;
        }
        checkIndexParam(index);
        T res = null;
        if (index == size - 1) {//last
            res = array[size - 1];
            array[--size] = null;
        } else if (index == 0) {//first
            res = array[0];
            array[0] = null;
            if (size > 1) {
                System.arraycopy(array, 1, array, 0, size - 1);
            }
            size--;
        } else {//middle
            res = array[index];
            array[index] = null;
            System.arraycopy(array, index + 1, array, index, size - index);
            size--;
        }
        return res;
    }

    /**
     * [A, B, C] size = 3
     * we can get value by index between [0, size - 1]
     * otherwise throw new IndexOutOfBoundsException
     */
    @Override
    public T get(int index) {
        checkIndexParam(index);
        return array[index];
    }

    /**
     * we can set value by index between [0, size - 1]
     * otherwise throw new IndexOutOfBoundsException
     */
    @Override
    public T set(T value, int index) {
        checkValueParam(value);
        checkIndexParam(index);
        array[index] = value;
        return value;
    }

    @Override
    public void clear() {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                array[i] = null;
            }
            size = 0;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    @Override
    public boolean contains(T value) {
        checkValueParam(value);
        return indexOf(value) == -1 ? false : true;
    }

    private int indexOf(T value, boolean startFromFirst) {
        checkValueParam(value);

        int i = 0;

        while (i < size) {
            int iter = (startFromFirst ? 0 : size - 1) - (i++) * (startFromFirst ? -1 : 1);
            if (array[iter] == value || array[iter].equals(value)) {
                return iter;
            }
        }

        return -1;
    }

    @Override
    public int indexOf(T value) {
        return indexOf(value, true);
    }

    @Override
    public int lastIndexOf(T value) {
        return indexOf(value, false);
    }

    @Override
    public String toString() {
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return "ArrayList{ \n" +
                "   array=" + Arrays.toString(newArray) + "\n" +
                "   size=" + size + "\n" +
                "   empty_slots=" + (array.length - size) + "\n" +
                '}';
    }

    @Override
    public Iterator iterator() {
        return new MyArrayIterator();
    }
    //==================================================================================================================

    //==================================================================================================================
    private class MyArrayIterator implements Iterator {
        private int index=-1;

        @Override
        public boolean hasNext() {
            if (index < size()-1) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            return array[++index];
        }

        @Override
        public void remove() {
           ArrayList.this.remove(index);
           index--;
        }
    }
}
