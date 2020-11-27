public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
                    while (true){
                        if(Thread.currentThread().isInterrupted()){
                            System.out.println("interrupted!");
                            break;
                        }

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            System.out.println("Interrupted when sleep");
                            Thread.currentThread().interrupt();
                        }
                        Thread.yield();
                    }

        });

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();

    }
}
