package producerConsumer;

//тестируем производителя и потребителя
public class QueueExampleMainProducerConsumer {
    public static void main(String[] args) {
        ElementQueue queue = new ElementQueue(4);
        new Thread(new ProducerQueue(1, 1000, queue)).start();
        new Thread(new ConsumerQueue(queue)).start();
    }
}
