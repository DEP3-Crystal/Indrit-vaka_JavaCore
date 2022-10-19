import java.util.concurrent.*;

public class Incrementer {
    private static int a;

    public synchronized int increment(){
        return  ++a;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Incrementer incrementer = new Incrementer();


        for (int i = 0; i < 10_000; i++) {
            new Thread(() -> {
            }).start();

        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> submit = executorService.submit(() -> {
            System.out.println(incrementer.increment());

        });
        submit.get(1_000, TimeUnit.MILLISECONDS);
    }
}
