package d3.queue;

class QueueTest{
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("A");

        System.out.println(queue.size()); // 3
        queue.removeAll("A");
        System.out.println(queue.size()); // 1
    }
}