public class JoinMain {
    public volatile static int i = 0;

   public static class AddThread extends Thread{
       public void run(){
           for (i = 0; i < 100; i++);
       }
   }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
