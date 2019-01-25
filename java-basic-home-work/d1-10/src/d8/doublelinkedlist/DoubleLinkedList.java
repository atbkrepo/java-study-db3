/**
 * Double Linked list
 * with Head and Tail
 * Node<T> with Next and Prev
 * methods based on recursion
 */
package d8.doublelinkedlist;

import d8.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements List<T>, Iterable {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public DoubleLinkedList(T value) {
        Node<T> node = new Node<T>(value);
        this.head = node;
        this.tail = node;
        size = 1;
    }

    private void checkIndexParam(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + (size - 1));
        }
    }

  /*  private void checkValueParam(Object value) {
        if (value == null) {
            throw new InvalidParameterException("Value can't be null");
        }
    }*/

    //==================================================================================================================

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            this.tail = newNode;
        }
        this.size++;
    }

    //from start bu index
    private void add(Node<T> node, T value, int position) {
        checkIndexParam(position);
        Node<T> newNode = new Node<T>(value);

        if (this.size == 0 || position == this.size) {
            add(value);
        } else if (position - 1 == 0) {
            newNode.next = node.next;
            node.next = newNode;
            newNode.prev = node;
            if (newNode.next != null) {
                newNode.next.prev = newNode;
            }
            this.size++;
        } else if (position == 0) {//node
            newNode.next = node;
            node.prev = newNode;
            this.head = newNode;
            this.size++;
        } else {
            add(node.next, value, --position);
        }
    }

    @Override
    public void add(T value, int index) {
        add(this.head, value, index);
    }

    //==================================================================================================================
    @Override
    public T remove(int index) {
        try {
            checkIndexParam(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        Node<T> nodeForDeletion;
        if (index <= this.size / 2) {
            nodeForDeletion = getByNext(head, index);
        } else {
            nodeForDeletion = getByLast(tail, index);
        }

        if (nodeForDeletion.prev != null) {//tail
            nodeForDeletion.prev.next = nodeForDeletion.next;
            if (nodeForDeletion == this.tail) {
                this.tail = nodeForDeletion.prev;
            }
        }
        if (nodeForDeletion.next != null) {//head
            nodeForDeletion.next.prev = nodeForDeletion.prev;
            if (nodeForDeletion == this.head) {
                this.head = nodeForDeletion.next;
            }
        }
        this.size--;
        return nodeForDeletion.value;
    }

    //==================================================================================================================
    //iter from head
    private Node<T> getByNext(Node<T> node, int index) {
        if (index == 0) {
            return node;
        } else {
            return getByNext(node.next, --index);
        }
    }

    //iter from tail
    private Node<T> getByLast(Node<T> node, int index) {
        if (this.size - index == 1) {
            return node;
        } else {
            return getByLast(node.prev, ++index);
        }
    }

    @Override
    public T get(int index) {
        checkIndexParam(index);
        Node<T> node;
        if (index <= this.size / 2) {
            node = getByNext(head, index);
        } else {
            node = getByLast(tail, index);
        }
        return node.value;
    }

    //==================================================================================================================
    @Override
    public T set(T value, int index) {
        checkIndexParam(index);
        Node<T> node;
        if (index <= this.size / 2) {
            node = getByNext(head, index);
        } else {
            node = getByLast(tail, index);
        }
        node.value = value;
        return value;
    }

    //==================================================================================================================
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //==================================================================================================================
    @Override
    public int size() {
        return this.size;
    }

    //==================================================================================================================
    @Override
    public boolean isEmpty() {
        return this.size == 0 && this.head == null && this.tail == null;
    }

    //==================================================================================================================
    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    //==================================================================================================================
    private int indexOfAny(Node<T> node, T value, int position, boolean fromHead) {
        if (position >= this.size || this.size == 0 || position < 0) {
            return -1;
        }
        if (value.equals(node.value)) {
            return position;
        } else {
            if (fromHead) {
                return indexOfAny(node.next, value, ++position, fromHead);
            } else {
                return indexOfAny(node.prev, value, --position, fromHead);
            }

        }
    }

    @Override
    public int indexOf(T value) {
        return indexOfAny(this.head, value, 0, true);
    }

    @Override
    public int lastIndexOf(T value) {
        return indexOfAny(this.tail, value, this.size - 1, false);
    }

    //==================================================================================================================
    @Override
    public String toString() {
        return "DoubleLinkedList{" +
                "head=" + head +
                ", size=" + size +
                '}';
    }

    @Override
    public Iterator iterator() {
        return new MyListIterator();
    }
    //==================================================================================================================

    public static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    //", prev=" + prev +
                    '}';
        }

    }

    //==================================================================================================================
    //==================================================================================================================
    private class MyListIterator implements Iterator<T> {
        private int index = -1;
        private Node<T> node = null;
        private Node<T> nodeP = null;

        @Override
        public boolean hasNext() {
            if (index < size() - 1) {
                return true;
            } else {
                return false;
            }

        }

        @Override
        public T next() {
            if (head == null) {
                throw new NoSuchElementException();
            }

            ++index;
            nodeP = node;
            if (node == null) {
                node = head;

            } else {
                node = node.next;
            }
            return node.value;
        }

        @Override
        public void remove() {
            if (node == null) {
                return;
            }

            if (node.next == null) {
                if (nodeP != null) {
                    nodeP.next = null;
                    tail = nodeP;
                }
                node = null;
                //head = null;
                //tail = null;
                size--;
                index--;
            } else {
                if (nodeP != null) {
                    nodeP.next = node.next;
                    node = nodeP;
                } else {
                    head = node.next;
                    node = null;
                }
                size--;
                index--;
            }
            if(size == 0){
                head=null;
                tail=null;
            }
        }
    }
}

