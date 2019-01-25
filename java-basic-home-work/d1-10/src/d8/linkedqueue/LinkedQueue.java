/**Double Linked Queue
 * with head only
 * Node with prev only
 */
package d8.linkedqueue;

public class LinkedQueue<E> {
    Node<E> head;
    int size;

    private Node<E> getTailNode(Node<E> startNode) {
        if (startNode == null) {
            return null;
        }
        Node<E> loopNode = startNode;
        while (loopNode.prev != null) {
            loopNode = loopNode.prev;
        }
        return loopNode;
    }


    void enqueue(E value) {
        Node<E> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            getTailNode(head).prev = newNode;
        }
        size++;
    }

    E dequeue() {
        if (size < 1) {
            return null;
        }
        E object = peek();
        head = head.prev;
        size--;
        return object;
    }

    //get
    E peek() {
        return head.value;
    }


    void removeAll(E value) {
        Node<E> loopNode = head;
        Node<E> beforeNode = null;//aka next in Q

        while (loopNode != null) {
            if (loopNode.value.equals(value)) {
                if (loopNode == head) {//head
                    head = loopNode.prev;
                    loopNode.prev = null;
                    loopNode = head;//iter
                    beforeNode = null;
                } else if (loopNode.prev == null) {//last
                    if (beforeNode != null) {
                        beforeNode.prev = null;
                        loopNode = loopNode.prev;//iter;
                    }
                } else {//middle
                    if (beforeNode != null) {
                        beforeNode.prev = loopNode.prev;
                        loopNode = loopNode.prev;//iter;
                    }
                }
                size--;
            } else {
                beforeNode = loopNode;
                loopNode = loopNode.prev;//iter;
            }
        }

    }

    @Override
    public String toString() {
        return "LinkedQueue{" +
                "head=" + head +
                ", size=" + size +
                '}';
    }

    //=================================
    public class Node<T> {
        T value;
        Node<T> prev;

        Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", prev=" + prev +
                    '}';
        }
    }
}