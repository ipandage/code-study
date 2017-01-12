package thread;

/**
 * Created with IntelliJ IDEA.
 * User: gao
 * Date: 17-1-12
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class MyRunnable implements Runnable {
    private Foo foo = new Foo();

    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread ta = new Thread(r, "Thread-A");
        Thread tb = new Thread(r, "Thread-B");
        ta.start();
        tb.start();
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            this.fix(30);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : 当前foo对象的x值= " + foo.getX());
        }
    }

    public int fix(int y) {
        return foo.fix(y);
    }
}
