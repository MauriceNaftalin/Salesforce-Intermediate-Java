package sfij.concurrency.exercise2;

public class Driver {

	public static void main(String[] args) {

		// Create a catalogue and add some products.
		Catalogue catalogue = new Catalogue();
		catalogue.addProduct(new Product("Jeans", 45.00));
		catalogue.addProduct(new Product("Shirt", 35.00));
		catalogue.addProduct(new Product("Sweater", 75.00));
		catalogue.addProduct(new Product("Scarf", 15.00));
		
		// TODO: Create an order queue, which should be an instance of BlockingQueue.

		// TODO: Create an OrderDispatcher, supplying it with a reference to the order queue
		//       and the catalogue. OrderDispatcher implements Runnable, so it's straightforward
		//       to start it running in its own thread.

		// TODO: Create a collection of Customer objects, supplying each with a reference
		//       to the order queue and the catalogue. Since you will have made Customer a
		//       Runnable, you can make each customer run in a separate thread.
		//       For a bonus, don't let any customer start shopping until all are ready.
	}
}
