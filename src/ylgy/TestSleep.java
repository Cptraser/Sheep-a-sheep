package ylgy;

public class TestSleep  implements Runnable{
    private int money=5;
    @Override
    public void run() {
        buy();
    }
    public synchronized void buy(){ //synchronized 修饰的方法就是同步方法每次只能一个对象调用
        for(int i=0;i<10;i++){
            if (money > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("购物" + Thread.currentThread().getName() + ":money = " + money--);
            }
        }
    }
}

class MySleep{
    public static void main(String[] args) {
        TestSleep testSleep = new TestSleep();
        Thread t1 = new Thread(testSleep,"A");
        Thread t2 = new Thread(testSleep,"B");
        Thread t3 = new Thread(testSleep,"C");
        t1.start();
        t2.start();
        t3.start();
    }
}
