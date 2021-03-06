package producerConsumer;

import java.util.concurrent.LinkedBlockingQueue;

//Класс для реалзации шаблона Производитель-потребитель с помощью очереди (для связи двух процессов испльзуется
//очередь)
// класс наследуется от public class LinkedBlockingQueue<E>  - дополнительно ограниченная очередь блокирования,
// основанная на соединенных узлах. Эта очередь упорядочивает FIFO элементов (первым прибыл - первым убыл).
// Глава очереди - то, что элемент, который был на очереди самым долгим временем.
// Хвост очереди - то, что элемент, который был на очереди самым коротким временем.
// Новые элементы вставляются в хвосте очереди, и операции извлечения очереди получают элементы во главе очереди.
// У соединенных очередей обычно есть более высокая пропускная способность чем основанные на массиве очереди,
// но менее предсказуемая производительность в большинстве параллельных приложений.
// Если потоки, разгребающие очередь перестанут справляться с наплывом данных,
// то можно довольно быстро схлопотать out of memory или перегрузить IO/Net настолько,
// что производительность упадет в разы пока не настанет отказ системы по таймаутам
// или из за отсутствия свободных дескрипторов в системе.
// Для таких случаев нужна queue с возможностью задать размер очереди или с блокировками по условиям.

public class ElementQueue extends LinkedBlockingQueue {
    private Integer elem = null;

    private LinkedBlockingQueue queue;

// конструктор по умолчанию
    public ElementQueue(){
        this.queue = new LinkedBlockingQueue();

    }


// конструктор с параметром для создания очереди с фиксированной емкостью для предотвращения
// чрезмерного расширения очереди
    public ElementQueue(final Integer MAX_VALUE ){
        this.queue = new LinkedBlockingQueue(MAX_VALUE);

    }



// метод реализует добавление элемента в конец очереди
    public void put(Integer elem) throws InterruptedException{
        synchronized (queue) {
            //offer(E e)Вставляет указанный элемент в хвосте этой очереди, если возможно сделать так сразу,
            // не превышая емкость очереди, возвращаясь true на успех и false если эта очередь полна.
            // Тогда ждем, пока процесс потребитель не получит и удалит из начала очереди
            // элемент, освободив тем самым емкость очереди

            while (queue.offer(elem)==false){
                queue.wait();
            }

            queue.notify();
        }
    }
// метод реализует получение элемента из начала очереди
    public Integer get() throws InterruptedException{
        synchronized (queue) {
        //если очередь пустая, ждем пока производитель не добавит элемент
        while (queue.isEmpty()) {
            queue.wait();
        }

            Integer result = (Integer) queue.poll();  //Получает и удаляет главу этой очереди, или возвращает null, если эта очередь пуста.
            queue.notify();
            return result;
        }
    }
}

