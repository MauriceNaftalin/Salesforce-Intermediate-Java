package sfij.concurrency.solutions.exercise2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class OrderDispatcher implements Runnable {

    private final BlockingQueue<Order> orderQueue;
    private final Catalogue catalogue;

    public OrderDispatcher(BlockingQueue<Order> orderQueue, Catalogue catalogue) {
        this.orderQueue = orderQueue;
        this.catalogue = catalogue;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (true) {
            try {
                Thread.sleep(r.nextInt(2000));
                // TODO get an order from orderQueue
                // TODO call the updateProductInventory method of Catalogue,
                //      supplying the productId from the order
                Order order = orderQueue.take();
                catalogue.updateProductInventory(order.getProductId());
            } catch (InterruptedException ex) {
                System.out.println("OrderDispatcher interrupted.");
            }
        }
    }
}
