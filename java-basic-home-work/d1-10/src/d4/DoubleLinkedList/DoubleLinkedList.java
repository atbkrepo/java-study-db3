/**Double Linked list
 * with Head and Tail
 * Node with Next and Prev
 * methods based on recursion
 */
package d4.DoubleLinkedList;

import d4.List;

public class DoubleLinkedList implements List {
    private Node head;
    private Node tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public DoubleLinkedList(Object value) {
        Node node = new Node(value);
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
    public void add(Object value) {
        Node newNode = new Node(value);
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
    private void add(Node node, Object value, int position) {
        checkIndexParam(position);
        Node newNode = new Node(value);

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
        Node nodeForDeletion;
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
    private Node getByNext(Node node, int index) {
        if (index == 0) {
            return node;
        } else {
            return getByNext(node.next, --index);
        }
    }

    //iter from tail
    private Node getByLast(Node node, int index) {
        if (this.size - index == 1) {
            return node;
        } else {
            return getByLast(node.prev, ++index);
        }
    }

    @Override
    public Object get(int index) {
        checkIndexParam(index);
        Node node;
        if (index <= this.size / 2) {
            node = getByNext(head, index);
        } else {
            node = getByLast(tail, index);
        }
        return node.value;
    }

    //==================================================================================================================
    @Override
    public Object set(Object value, int index) {
        checkIndexParam(index);
        Node node;
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
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    //==================================================================================================================
    private int indexOfAny(Node node, Object value, int position, boolean fromHead) {
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
    public int indexOf(Object value) {
        return indexOfAny(this.head, value, 0, true);
    }

    @Override
    public int lastIndexOf(Object value) {
        return indexOfAny(this.tail, value, this.size-1, false);
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
        Node prev;

        public Node(Object value) {
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
}
