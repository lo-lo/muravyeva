package producerConsumer;

//тестируем процесс-потребитель
public class ConsumerQueueMain {

    public static void main(String[] args) {
        ElementQueue queue = new ElementQueue(1);
        new Thread(new ConsumerQueue(queue)).start();

    }
}
