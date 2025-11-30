import java.util.concurrent.*;
import java.util.*;

public class ParallelMonteCarloPi {
    private static final long ITERATIONS = 1_000_000_000L;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        if (args.length != 1) {
            System.out.println("java ParallelMonteCarloPi <thread_count>");
            return;
        }

        int numThreads = Integer.parseInt(args[0]);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        long start = System.currentTimeMillis();

        long iterationsPerThread = ITERATIONS / numThreads;
        List<Future<Long>> results = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            results.add(executor.submit(() -> {
                Random rand = new Random();
                long insideCircle = 0;
                for (long j = 0; j < iterationsPerThread; j++) {
                    double x = rand.nextDouble();
                    double y = rand.nextDouble();
                    if (x * x + y * y <= 1.0)
                        insideCircle++;
                }
                return insideCircle;
            }));
        }

        long totalInside = 0;
        for (Future<Long> result : results) {
            totalInside += result.get();
        }

        executor.shutdown();

        double pi = 4.0 * totalInside / ITERATIONS;
        long end = System.currentTimeMillis();

        System.out.printf("PI is %.5f%n", pi);
        System.out.println("THREADS " + numThreads);
        System.out.printf("ITERATIONS %,d%n", ITERATIONS);
        System.out.printf("TIME %.2f ms%n", (end - start) * 1.0);
    }
}
