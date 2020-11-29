

public class PlusTask {
    static volatile int t=0;
    public static  void main(String[] args) throws InterruptedException {
        synchronized(PlusTask.class){
            for (int i = 0; i < 10; i++) {
                new Thread(()->{
                    t++;
                }).start();
            }
        }


        System.out.println(t);

    }
}
