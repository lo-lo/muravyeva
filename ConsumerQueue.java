package producerConsumer;

//класс реализует получение потребителем элемента из очереди
public class ConsumerQueue implements Runnable {

    private final ElementQueue queue;

    public ConsumerQueue(ElementQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //получаем из очереди элемент и выводим его значение
                Integer elem = queue.get();
                System.out.println("Value = " + elem);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "is interrupted");
                return;
            }
        }
    }
}





