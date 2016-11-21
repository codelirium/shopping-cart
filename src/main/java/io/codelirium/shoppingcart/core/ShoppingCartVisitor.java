package io.codelirium.shoppingcart.core;

import io.codelirium.shoppingcart.domain.product.ShopProduct;


public interface ShoppingCartVisitor {

	void addProduct(final ShopProduct shopProduct);

	void removeProduct(final ShopProduct shopProduct);

}
