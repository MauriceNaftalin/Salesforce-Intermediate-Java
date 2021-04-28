package sfij.concurrency.exercise2;

public class Product {

	private final String description;
	private final double price;
	private int numberSold;
	
	public Product(String description, double price) {
		this.description = description;
		this.price = price;
		this.numberSold = 0;
	}

	public void sellOneOf() {
		numberSold++;
		System.out.printf("   PROD SOLD:  Product %s just sold, total sold so far %d%n", description, numberSold);
	}

	@Override
	public String toString() {
		return String.format("Product [description=%s, price=%s, numberSold=%s]", 
				              description, price, numberSold);
	}
}
