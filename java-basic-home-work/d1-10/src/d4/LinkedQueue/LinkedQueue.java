/**Double Linked Queue
 * with head only
 * Node with prev only
 */
package d4.LinkedQueue;

public class LinkedQueue {
    Node head;
    int size;

    private Node getTailNode(Node startNode) {
        if (startNode == null) {
            return null;
        }
        Node loopNode = startNode;
        while (loopNode.prev != null) {
            loopNode = loopNode.prev;
        }
        return loopNode;
    }


    void enqueue(Object value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            getTailNode(head).prev = newNode;
        }
        size++;
    }

    Object dequeue() {
        if (size < 1) {
            return null;
        }
        Object object = peek();
        head = head.prev;
        size--;
        return object;
    }

    //get
    Object peek() {
        return head.value;
    }


    void removeAll(Object value) {
        Node loopNode = head;
        Node beforeNode = null;//aka next in Q

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
    public class Node {
        Object value;
        Node prev;

        Node(Object value) {
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