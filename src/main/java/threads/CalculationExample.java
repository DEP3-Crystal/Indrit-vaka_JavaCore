package threads;

public class CalculationExample {
   static int sum;
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Starting computation...");
            for (int i = 0; i < 100_000; i++) {
                sum += i;
            }
            System.out.println("Computation finished!");
        });
        thread.start();

        while (thread.getState() != Thread.State.TERMINATED){
            System.out.println("Waiting for the result...");
        }
        System.out.println(sum);


    }
}
