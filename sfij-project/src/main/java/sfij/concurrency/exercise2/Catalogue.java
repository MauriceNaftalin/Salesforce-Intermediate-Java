package sfij.concurrency.exercise2;

import java.util.HashMap;
import java.util.Random;

public class Catalogue {

	private int productCount = 0;

	// TODO: Make this map thread-safe
	private HashMap<Integer, Product> products = new HashMap<>();
	
	public void addProduct(Product product) {
		products.put(productCount++, product);
	}
	
	public int getRandomProduct() {
		Random r = new Random();
		return r.nextInt(productCount);
	}

	public void updateProductInventory(int productId) {
		Product product = products.get(productId);
		if (product != null) {
			product.sellOneOf();
		}
	}
}
