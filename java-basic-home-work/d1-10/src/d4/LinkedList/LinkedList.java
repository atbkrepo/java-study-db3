/**Linked list
 * with Head only
 * Node with Next only
 * methods based on recursion
 */
package d4.LinkedList;

import d4.List;

public class LinkedList implements List {
    private Node head;
    private int size;

    public LinkedList() {
        this.head = null;
        size = 0;
    }

    public LinkedList(Object value) {
        this.head = new Node(value);
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
    private void add(Node node, Object value) {
        if (node == null) {
            this.head = new Node(value);
            this.size++;
        } else {
            if (node.next == null) {
                Node newNode = new Node(value);
                node.next = newNode;
                this.size++;
            } else {
                add(node.next, value);
            }
        }
    }

    @Override
    public void add(Object value) {
        add(this.head, value);
    }

    private void add(Node node, Object value, int position) {
        checkIndexParam(position);
        Node newNode = new Node(value);

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
    public void add(Object value, int index) {
        add(this.head, value, index);
    }

    //==================================================================================================================
    @Override
    public Object remove(int index) {
        try {
            checkIndexParam(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        Node nodeForDeletion = getByNext(head, index);

        if (nodeForDeletion == this.head) {
            this.head = nodeForDeletion.next;
        }

        this.size--;
        return nodeForDeletion.value;
    }

    //==================================================================================================================
    private Node getByNext(Node node, int index) {
        if (index == 0) {
            return node;
        } else {
            return getByNext(node.next, --index);
        }
    }

    @Override
    public Object get(int index) {
        checkIndexParam(index);
        return getByNext(head, index).value;
    }

    //==================================================================================================================
    @Override
    public Object set(Object value, int index) {
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
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    //==================================================================================================================
    private int indexOfAny(Node node, Object value, int position, int maxPos, boolean fromHead) {
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
    public int indexOf(Object value) {
        return indexOfAny(this.head, value, 0, -1, true);
    }

    @Override
    public int lastIndexOf(Object value) {
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
    //==================================================================================================================

    public static class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }

    }

}
