package d5.linkedqueue;

public class Test {
    public static void main(String[] args) {
        LinkedQueue linkedQueue = new LinkedQueue();

        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");
        linkedQueue.enqueue("C");
        //=================
        System.out.println(linkedQueue);
        //=================
        System.out.println(linkedQueue.peek());
        //=================
        System.out.println();
        System.out.println();
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size);

        System.out.println();
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size);

        System.out.println();
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size);

        System.out.println();
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size);

        //=================
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("A");
        linkedQueue.enqueue("B");

        linkedQueue.removeAll("A");
        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size);

    }
}
