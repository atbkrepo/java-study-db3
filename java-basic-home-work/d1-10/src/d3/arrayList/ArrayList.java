package d3.arrayList;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class ArrayList implements List {

    private Object[] array;
    private int size;

    public ArrayList() {
        this(4);
    }

    public ArrayList(Object[] array) {
        this.array = array;
        this.size = array.length;
    }

    public ArrayList(int initialSize) {
        size = 0;
        array = new Object[initialSize];
    }

    private void extend() {
        if (array[array.length - 1] != null) {
            Object[] newArray = new Object[(int) (size * 1.5)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndexParam(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + (size - 1));
        }
    }

    private void checkValueParam(Object value) {
        if (value == null) {
            throw new InvalidParameterException("Value can't be null");
        }
    }

    /**
     * add value to the end of the list
     **/
    @Override
    public void add(Object value) {
        add(value, size);
    }

    /**
     * we can add value by index between [0, size]
     * otherwise throw new IndexOutOfBoundsException
     * [A, B, C] . add("D", [0, 1, 2, 3])
     **/
    @Override
    public void add(Object value, int index) {
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
    public boolean remove(int index) {
        if (size == 0) {
            return false;
        }
        checkIndexParam(index);
        if (index == size - 1) {//last
            array[--size] = null;
        } else if (index == 0) {//first
            array[0] = null;
            if (size > 1) {
                System.arraycopy(array, 1, array, 0, size - 1);
            }
            size--;
        } else {//middle
            array[index] = null;
            System.arraycopy(array, index + 1, array, index, size - index);
            size--;
        }
        return true;
    }

    /**
     * [A, B, C] size = 3
     * we can get value by index between [0, size - 1]
     * otherwise throw new IndexOutOfBoundsException
     */
    @Override
    public Object get(int index) {
        checkIndexParam(index);
        return array[index];
    }

    /**
     * we can set value by index between [0, size - 1]
     * otherwise throw new IndexOutOfBoundsException
     */
    @Override
    public boolean set(Object value, int index) {
        checkValueParam(value);
        checkIndexParam(index);
        array[index] = value;
        return true;
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
    public boolean contains(Object value) {
        checkValueParam(value);
        return indexOf(value) == -1 ? false : true;
    }

    private int indexOf(Object value, boolean startFromFirst) {
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
    public int indexOf(Object value) {
        return indexOf(value, true);
    }

    @Override
    public int lastIndexOf(Object value) {
        return indexOf(value, false);
    }

    @Override
    public String toString() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return "ArrayList{ \n" +
                "   array=" + Arrays.toString(newArray) + "\n" +
                "   size=" + size + "\n" +
                "   empty_slots=" + (array.length - size) + "\n" +
                '}';
    }
}
