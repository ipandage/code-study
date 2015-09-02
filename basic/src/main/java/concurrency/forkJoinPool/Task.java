package concurrency.forkJoinPool;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gaoxingang
 * Date: 14-5-27
 * Time: 下午5:55
 * To change this template use File | Settings | File Templates.
 */
public class Task extends RecursiveAction {

    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        System.out.println("111");
        if (last - first < 10) {
            updatePrices();
        } else {
            int middle = (last + first) / 2;
            // System.out.printf("Task: Pending tasks: %s\n",getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            invokeAll(t1, t2);
        }
    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }

}
