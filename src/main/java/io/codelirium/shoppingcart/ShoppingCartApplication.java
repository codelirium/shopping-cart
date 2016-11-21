package io.codelirium.shoppingcart;

import io.codelirium.shoppingcart.core.ShoppingCart;
import io.codelirium.shoppingcart.domain.product.ShopProduct;
import io.codelirium.shoppingcart.util.RandomUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;


@SpringBootApplication
public class ShoppingCartApplication implements CommandLineRunner {

	public static void main(final String[] args) {

		SpringApplication.run(ShoppingCartApplication.class, args);

	}

	@Override
	public void run(final String... strings) throws Exception {

		final List<ShopProduct> shopBasket = RandomUtil.createRandomShopBasket(10);


		final ShoppingCart cart = new ShoppingCart();

		shopBasket.forEach(shopProduct -> shopProduct.addToCart(cart));


		System.out.println("Shopping cart before checkout: " + cart.toString());

		cart.checkout(RandomUtil.createRandomMasterCard());

		System.out.println("Shopping cart after checkout: " + cart.toString());


		System.out.println("Basket total price: " + cart.calculateTotalCost());

	}
}
