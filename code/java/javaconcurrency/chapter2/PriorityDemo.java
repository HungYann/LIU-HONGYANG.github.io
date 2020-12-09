public class PriorityDemo {

    public static void main(String[] args) {
        //主线程优先级
        System.out.println(Thread.currentThread().getName()+"----->"+Thread.currentThread().getPriority());
        MyPriority myPriority = new MyPriority();
        Thread t1 = new Thread(myPriority);
        Thread t2 = new Thread(myPriority);
        Thread t3 = new Thread(myPriority);
        Thread t4 = new Thread(myPriority);
        Thread t5 = new Thread(myPriority);
        Thread t6 = new Thread(myPriority);

        t1.start();

        t2.setPriority(1);
        t2.start();

        t3.setPriority(4);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();

//        t5.setPriority(-1);
//        t5.start();
//
//        t6.setPriority(11);
//        t6.start();


    }
}

class MyPriority implements Runnable{
    public void run(){
        System.out.println(Thread.currentThread().getName()+"---->"+ Thread.currentThread().getPriority());
    }
}