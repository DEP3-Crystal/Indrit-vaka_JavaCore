public class Main {
    public static int a;

    public static void main(String[] args) {

        for (int i = 0; i < 10_000; i++) {
            new Thread(() -> {
                synchronized (Main.class) {
                    a++;
                }
                System.out.println(a);
            }).start();

        }

    }
}
