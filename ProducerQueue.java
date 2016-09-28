package producerConsumer;


//класс реализет добавление производилелем элемента в очередь
public class ProducerQueue implements Runnable {
    private int startValue;
    private final int period;

    private final ElementQueue queue;


    public ProducerQueue(int startValue, int period, ElementQueue queue) {
        this.queue = queue;
        this.period = period;
        this.startValue = startValue;
    }



    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(startValue + " produced");
                queue.put(startValue++);
                Thread.sleep(period);

            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " stopped.");
                return;
            }
        }



    }
}
