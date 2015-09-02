package concurrency.forkJoinPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gaoxingang
 * Date: 14-5-27
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
public class ProductListGenerator {

    public List<Product> generate(int size) {

        List<Product> ret = new ArrayList<Product>();

        for (int i = 0; i < size; i++) {

            Product product = new Product();

            product.setName("Product " + i);

            product.setPrice(10);

            ret.add(product);

        }
        return ret;
    }
}
