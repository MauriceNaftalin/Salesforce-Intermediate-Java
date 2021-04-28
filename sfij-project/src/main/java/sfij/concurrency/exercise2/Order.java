package sfij.concurrency.exercise2;

public class Order {
	public final int productId;
	public final Customer customer;
	
	public Order(int productId, Customer customer) {
		this.productId = productId;
		this.customer = customer;
	}
}
