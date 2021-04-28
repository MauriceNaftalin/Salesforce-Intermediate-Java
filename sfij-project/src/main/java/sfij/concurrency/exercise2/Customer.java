package sfij.concurrency.exercise2;

// TODO Make the Customer class implement Runnable
public class Customer {

    // TODO Provide a constructor that will initialise fields storing the name, orderQueue and catalogue
    //      references
    // public Customer(String name, BlockingQueue<Order> orderQueue, Catalogue catalogue) {...}

    // Behave like a real customer – that is, repeatedly get random product IDs from the catalogue at
	// intervals (specified by the delay argument to the constructor), and put them on the order queue
    // for the OrderDispatcher to process. Even real customers have a limit, so stick to a maximum number
    // of orders.

    // TODO Uncomment the run() method
/*
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                int productId = catalogue.getRandomProduct();
                System.out.printf("ORDER: Product %d ordered by customer %s%n", productId, name);
                // TODO Place an order on the order queue
                orderQueue.offer(new Order(productId, this));
            }
            catch (InterruptedException ex) {
                System.out.println("Customer " + name + " interrupted.");
            }
        }
    }
*/

}
