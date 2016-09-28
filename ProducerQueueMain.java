package producerConsumer;

//тестируем "производителя"
public class ProducerQueueMain {
    public static void main(String[] args) {
        ElementQueue queue = new ElementQueue(5);
        new Thread(new ProducerQueue(1, 1000, queue)).start();

    }
}
