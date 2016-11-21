package io.codelirium.shoppingcart.util;

import com.google.common.collect.Lists;
import io.codelirium.shoppingcart.domain.payment.impl.MasterCard;
import io.codelirium.shoppingcart.domain.payment.impl.Visa;
import io.codelirium.shoppingcart.domain.product.ShopProduct;
import io.codelirium.shoppingcart.domain.product.impl.ShopProductImpl;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;


public class RandomUtil {

	public static String SOME_RANDOM_SHOP_PRODUCT      = "SomeRandomShopProduct";
	public static double SOME_RANDOM_SHOP_PRODUCT_COST = 123.45;
	public static String SOME_RANDOM_CARD_NAME         = "someCardName";
	public static String SOME_RANDOM_CARD_NUMBER       = "1234567890";

	private RandomUtil() { }


	public static ShopProduct createRandomShopProduct(final Long id) {

		return new ShopProductImpl(id, SOME_RANDOM_SHOP_PRODUCT, SOME_RANDOM_SHOP_PRODUCT_COST);

	}

	public static List<ShopProduct> createRandomShopBasket(final int howManyProducts) {

		final List<ShopProduct> shopBasket = Lists.newLinkedList();

		LongStream.range(0, howManyProducts).forEach(id -> shopBasket.add(RandomUtil.createRandomShopProduct(id)));


		return shopBasket;
	}

	public static MasterCard createRandomMasterCard() {

		return new MasterCard(SOME_RANDOM_CARD_NAME, SOME_RANDOM_CARD_NUMBER, new Date());

	}

	public static Visa createRandomVisa() {

		return new Visa(SOME_RANDOM_CARD_NAME, SOME_RANDOM_CARD_NUMBER, new Date());

	}
}
