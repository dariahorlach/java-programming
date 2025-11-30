import java.util.*;

class RingBuffer<T> {
    private final Object[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    public RingBuffer(int capacity) {
        this.buffer = new Object[capacity];
    }

    public synchronized void put(T item) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
    }

    @SuppressWarnings("unchecked")
    public synchronized T get() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        T item = (T) buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        return item;
    }
}

public class RingBufferDemo {
    public static void main(String[] args) throws InterruptedException {
        RingBuffer<String> buffer1 = new RingBuffer<>(10);
        RingBuffer<String> buffer2 = new RingBuffer<>(10);

        for (int i = 1; i <= 5; i++) {
            final int id = i;
            Thread producer = new Thread(() -> {
                Random rand = new Random();
                int count = 0;
                while (true) {
                    try {
                        String msg = "Потік №" + id + " згенерував повідомлення " + (++count);
                        buffer1.put(msg);
                        Thread.sleep(rand.nextInt(50) + 10);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            producer.setDaemon(true);
            producer.start();
        }

        for (int i = 1; i <= 2; i++) {
            final int id = i;
            Thread translator = new Thread(() -> {
                while (true) {
                    try {
                        String msg = buffer1.get();
                        String translated = "Потік №" + id + " переклав повідомлення: " + msg;
                        buffer2.put(translated);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            translator.setDaemon(true);
            translator.start();
        }

        for (int i = 0; i < 100; i++) {
            String msg = buffer2.get();
            System.out.println(msg);
        }

        System.out.println("Кінець.");
    }
}
