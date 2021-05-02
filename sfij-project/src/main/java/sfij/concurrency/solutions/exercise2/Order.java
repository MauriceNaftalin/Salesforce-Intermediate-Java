package sfij.concurrency.solutions.exercise2;

public class Order {
	private final int productId;
	private final Customer customer;

	public int getProductId() {
		return productId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Order(int productId, Customer customer) {
		this.productId = productId;
		this.customer = customer;
	}
}
