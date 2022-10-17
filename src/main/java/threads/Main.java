package threads;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Initializing system...");
            for (int i = 0; i < 100_000_000; i++) {
                Math.random();
            }
            System.out.println("Initialized");
        }, "My Task");
        thread.start();
        //Thread.getAllStackTraces().keySet().forEach(System.out::println);

        System.out.println("Welcome to ATM");
        System.out.println("Chose form options bellow");
    }
}
