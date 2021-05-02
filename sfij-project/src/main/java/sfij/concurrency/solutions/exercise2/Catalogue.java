package sfij.concurrency.solutions.exercise2;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Catalogue {

	private int productCount = 0;

	// TODO: Make this map thread-safe
	private final Map<Integer, Product> products = new ConcurrentHashMap<>();
	
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
