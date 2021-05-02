package sfij.concurrency.solutions.exercise2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class Driver {

	public static void main(String[] args) {

		// Create a catalogue and add some products.
		Catalogue catalogue = new Catalogue();
		catalogue.addProduct(new Product("Jeans", 45.00));
		catalogue.addProduct(new Product("Shirt", 35.00));
		catalogue.addProduct(new Product("Sweater", 75.00));
		catalogue.addProduct(new Product("Scarf", 15.00));
		
		// TODO: Create an order queue, which should be an instance of BlockingQueue.
		BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

		// TODO: Create an OrderDispatcher, supplying it with a reference to the order queue
		//       and the catalogue. OrderDispatcher implements Runnable, so it's straightforward
		//       to start it running in its own thread.
		new Thread(new OrderDispatcher(orderQueue,catalogue)).start();

		// TODO: Create a collection of Customer objects, supplying each with a reference
		//       to the order queue and the catalogue. Since you will have made Customer a
		//       Runnable, you can make each customer run in a separate thread.
		//       For a bonus, don't let any customer start shopping until all are ready.
        Customer[] customers = new Customer[3];
        customers[0] = new Customer("Bill", orderQueue, catalogue);
        customers[1] = new Customer("Mary", orderQueue, catalogue);
        customers[2] = new Customer("Amy", orderQueue, catalogue);
		CountDownLatch latch = new CountDownLatch(3);
		for (Customer customer : customers) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					latch.countDown();
					try {
						latch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					customer.run();
				}
			}).start();
		}
	}
}
