package io.codelirium.shoppingcart.domain.product;

import io.codelirium.shoppingcart.core.ShoppingCartVisitor;


public interface ShopProduct {

	long getId();

	String getName();

	double getPrice();


	void addToCart(final ShoppingCartVisitor shoppingCartVisitor);

	void removeFromCart(final ShoppingCartVisitor shoppingCartVisitor);

}
