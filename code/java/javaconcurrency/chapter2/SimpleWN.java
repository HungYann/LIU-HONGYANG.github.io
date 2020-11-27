public class SimpleWN {
    final static Object object = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            synchronized (object){
                System.out.println(System.currentTimeMillis()+"T1: start!");
                try {
                    System.out.println(System.currentTimeMillis()+"T1: wait for object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+"T1: end!");
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (object){
                System.out.println(System.currentTimeMillis()+"T2: start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis()+"T2: end!");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();


    }
}
