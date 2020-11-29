public class NoVisibility {
    private static boolean ready;
    private static int number;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            while (!ready);
            System.out.println(number);
        });
        t1.start();

        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
