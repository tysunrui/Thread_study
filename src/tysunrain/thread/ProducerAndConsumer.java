package tysunrain.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: ProducerAndConsumer
 * @Description: 生产者消费者模式
 * @Author: Administrator
 * @Date: 2020/2/25 0025
 * @Version: 1.0
 **/
public class ProducerAndConsumer {
    public static void main(String[] args) {
        ProductPool productPool = new ProductPool();
        Producter producter = new Producter(productPool);
        Consumer consumer = new Consumer(productPool);
        new Thread(()->producter.generate()).start();
        new Thread(()->consumer.consume()).start();
    }
}


class Producter{
    private  ProductPool productPool;

    public Producter(ProductPool productPool) {
        this.productPool = productPool;
    }
    public void  generate(){
        while(true) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Product product = new Product("口罩", UUID.randomUUID().toString());
            System.out.println("生产了一件商品，信息如下" + product.toString());
            productPool.pushProduct(product);
        }
    }

}

class Consumer{
    private ProductPool productPool;

    public Consumer(ProductPool productPool) {
        this.productPool = productPool;
    }

    public void consume(){
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Product product = productPool.popProduct();
            System.out.println("消费者消费了一件商品商品信息如下"+product.toString());
        }
    }
}

class Product{
    private String name;
    private String productID;

    public Product(String name, String productID) {
        this.name = name;
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", productID='" + productID + '\'' +
                '}';
    }
}

class ProductPool {
    private int MAX_LIMIT = 100;
    private List<Product> productList = new LinkedList<>();

    public synchronized void pushProduct(Product product) {
        if (productList.size() >=MAX_LIMIT) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productList.add(product);
        this.notifyAll();
    }
        public synchronized Product popProduct () {
            if(productList.size()==0){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = productList.remove(0);
            if(productList.size()<MAX_LIMIT) {
                this.notifyAll();
            }
            return product;
        }
    }
