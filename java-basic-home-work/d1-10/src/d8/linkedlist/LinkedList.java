/**
 * Linked list
 * with Head only
 * Node<T> with Next only
 * methods based on recursion
 */
package d8.linkedlist;

import d8.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T>, Iterable {
    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        size = 0;
    }

    public LinkedList(T value) {
        this.head = new Node<T>(value);
        size = 1;
    }

    private void checkIndexParam(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + (size - 1));
        }
    }

  /*  private void checkValueParam(T value) {
        if (value == null) {
            throw new InvalidParameterException("Value can't be null");
        }
    }*/

    //==================================================================================================================
    private void add(Node<T> node, T value) {
        if (node == null) {
            this.head = new Node<T>(value);
            this.size++;
        } else {
            if (node.next == null) {
                Node<T> newNode = new Node<T>(value);
                node.next = newNode;
                this.size++;
            } else {
                add(node.next, value);
            }
        }
    }

    @Override
    public void add(T value) {
        add(this.head, value);
    }

    private void add(Node<T> node, T value, int position) {
        checkIndexParam(position);
        Node<T> newNode = new Node<T>(value);

        if (this.head == null) {
            this.head = newNode;
            this.size++;
        } else if (position - 1 == 0) {
            newNode.next = node.next;
            node.next = newNode;
            this.size++;
        } else if (position == 0) {//node
            newNode.next = node;
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

        Node<T> nodeForDeletion = getByNext(head, index);

        if (nodeForDeletion == this.head) {
            this.head = nodeForDeletion.next;
        }

        this.size--;
        return nodeForDeletion.value;
    }

    //==================================================================================================================
    private Node<T> getByNext(Node<T> node, int index) {
        if (index == 0) {
            return node;
        } else {
            return getByNext(node.next, --index);
        }
    }

    @Override
    public T get(int index) {
        checkIndexParam(index);
        return getByNext(head, index).value;
    }

    //==================================================================================================================
    @Override
    public T set(T value, int index) {
        checkIndexParam(index);
        getByNext(head, index).value = value;
        return value;
    }

    //==================================================================================================================
    @Override
    public void clear() {
        this.head = null;
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
        return this.size == 0 && this.head == null;
    }

    //==================================================================================================================
    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    //==================================================================================================================
    private int indexOfAny(Node<T> node, T value, int position, int maxPos, boolean fromHead) {
        if (position >= this.size || this.size == 0) {
            return maxPos;
        }
        if (value.equals(node.value)) {
            if (fromHead) {
                return position;
            } else {
                return indexOfAny(node.next, value, ++position, Math.max(position - 1, maxPos), fromHead);
            }
        } else {
            return indexOfAny(node.next, value, ++position, maxPos, fromHead);
        }
    }

    @Override
    public int indexOf(T value) {
        return indexOfAny(this.head, value, 0, -1, true);
    }

    @Override
    public int lastIndexOf(T value) {
        return indexOfAny(this.head, value, 0, -1, false);
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

    //==================================================================================================================
    public static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node<T>{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }

    }

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
                }
                node=null;
                //head=null;
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
            }
        }
    }
}
