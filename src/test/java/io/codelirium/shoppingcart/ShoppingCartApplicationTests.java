package io.codelirium.shoppingcart;

import io.codelirium.shoppingcart.core.ShoppingCart;
import io.codelirium.shoppingcart.domain.product.ShopProduct;
import io.codelirium.shoppingcart.util.RandomUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ShoppingCartApplicationTests {

	private ShoppingCart cart;


	@Before
	public void setUp() {

		cart = new ShoppingCart();

	}


	@Test
	public void testThatOneProductIsAddedToTheCart() {

		addRandomBasketToTheCart(1);

		assertThat(cart.getCartSize(), is(1));
		assertThat(cart.getCartProducts().stream().findFirst().get().getName(), is(RandomUtil.SOME_RANDOM_SHOP_PRODUCT));
		assertThat(cart.isCartCheckedOut(), is(Boolean.FALSE));

	}

	@Test
	public void testThatOneProductIsRemovedFromTheCart() {

		addRandomBasketToTheCart(1);

		cart.getCartProducts().forEach(cartProduct -> cartProduct.removeFromCart(cart));


		assertThat(cart.getCartSize(), is(0));

	}

	@Test
	public void testThatTheCostOfOneRandomProductIsCalculatedCorrectly() {

		addRandomBasketToTheCart(1);

		assertThat(cart.calculateTotalCost(), is(1 * RandomUtil.SOME_RANDOM_SHOP_PRODUCT_COST));

	}

	@Test
	public void testThatTenProductsAreAddedToTheCart() {

		addRandomBasketToTheCart(10);

		assertThat(cart.getCartSize(), is(10));
		cart.getCartProducts().forEach(cartProduct -> assertThat(cartProduct.getName(), is(RandomUtil.SOME_RANDOM_SHOP_PRODUCT)));
		assertThat(cart.isCartCheckedOut(), is(Boolean.FALSE));

	}

	@Test
	public void testThatTenProductsAreRemovedFromTheCart() {

		addRandomBasketToTheCart(10);

		cart.getCartProducts().forEach(cartProduct -> cartProduct.removeFromCart(cart));


		assertThat(cart.getCartSize(), is(0));

	}

	@Test
	public void testThatTheCostOfTenRandomProductsIsCalculatedCorrectly() {

		addRandomBasketToTheCart(10);

		assertThat(cart.calculateTotalCost(), is(10 * RandomUtil.SOME_RANDOM_SHOP_PRODUCT_COST));

	}

	@Test
	public void testThatCartChecksOutSuccessfullyWithMastercard() {

		addRandomBasketToTheCart(10);

		assertThat(cart.isCartCheckedOut(), is(Boolean.FALSE));
		assertThat(cart.checkout(RandomUtil.createRandomMasterCard()), is(true));
		assertThat(cart.isCartCheckedOut(), is(Boolean.TRUE));

	}

	@Test
	public void testThatCartChecksOutSuccessfullyWithVisa() {

		addRandomBasketToTheCart(10);

		assertThat(cart.isCartCheckedOut(), is(Boolean.FALSE));
		assertThat(cart.checkout(RandomUtil.createRandomVisa()), is(true));
		assertThat(cart.isCartCheckedOut(), is(Boolean.TRUE));

	}

	@Test
	public void testThatAProductCannotBeAddedToACheckedOutCart() {

		addRandomBasketToTheCart(1);

		cart.checkout(RandomUtil.createRandomVisa());

		RandomUtil.createRandomShopProduct(2L).addToCart(cart);


		assertThat(cart.getCartSize(), is(1));

	}

	@Test
	public void testThatAProductCannotBeRemovedFromACheckedOutCart() {

		addRandomBasketToTheCart(1);

		cart.checkout(RandomUtil.createRandomVisa());

		cart.getCartProducts().stream().findFirst().get().removeFromCart(cart);


		assertThat(cart.getCartSize(), is(1));

	}


	private void addRandomBasketToTheCart(final int basketSize) {

		Assert.isTrue(basketSize >= 0, "The basket size cannot be negative.");


		final List<ShopProduct> basket = RandomUtil.createRandomShopBasket(basketSize);

		basket.forEach(shopProduct -> shopProduct.addToCart(cart));

	}
}
