package d3.queue;

public class ArrayQueue {
    private int size;
    Object[] array;

    public ArrayQueue() {
        this.size = 0;
    }

    //put
    void enqueue(Object value) {
        Object[] newArray = new Object[++this.size];
        newArray[0] = value;
        if (this.array != null) {
            System.arraycopy(this.array, 0, newArray, 1, this.array.length);
        }
        this.array = newArray;
    }

    // get and remove
    Object dequeue() {
        Object value = peek();
        if (value == null) {
            return null;
        }
        System.arraycopy(this.array, 0, this.array, 0, this.array.length - 1);
        return value;
    }

    // get
    Object peek() {
        if (this.size == 0) {
            return null;
        }
        return this.array[this.size - 1];
    }

    int size() {
        return this.size;
    }


    void removeAll(Object value) {
        if (!(value == null || this.size == 0)) {
            Object[] newArray = new Object[this.size];

            int i = 0;
            for (Object object : this.array) {
                if (!value.equals(object)) {
                    newArray[i] = object;
                    i++;
                }
            }
            if ((i) != this.size) {
                this.size = i;
                System.arraycopy(newArray, 0, this.array, 0, i);

            }
        }
    }

}

