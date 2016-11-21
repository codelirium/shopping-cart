package io.codelirium.shoppingcart.domain.product.impl;

import com.google.common.base.MoreObjects;
import io.codelirium.shoppingcart.core.ShoppingCartVisitor;
import io.codelirium.shoppingcart.domain.product.ShopProduct;
import java.io.Serializable;
import java.util.Objects;


public final class ShopProductImpl implements ShopProduct, Serializable {

	private static final long serialVersionUID = 2211249035676018946L;


	private final long   id;
	private final String name;
	private final double price;


	public ShopProductImpl(final long id, final String name, final double price) {

		this.id    = id;
		this.name  = name;
		this.price = price;

	}


	@Override
	public long getId() {

		return id;

	}

	@Override
	public String getName() {

		return name;

	}

	@Override
	public double getPrice() {

		return price;

	}


	@Override
	public void addToCart(final ShoppingCartVisitor shoppingCartVisitor) {

		shoppingCartVisitor.addProduct(this);

	}

	@Override
	public void removeFromCart(final ShoppingCartVisitor shoppingCartVisitor) {

		shoppingCartVisitor.removeProduct(this);

	}


	@Override
	public boolean equals(final Object that) {

		return Objects.equals(this, that);

	}

	@Override
	public int hashCode() {

		return Objects.hashCode(this);

	}

	@Override
	public String toString() {

		return MoreObjects.toStringHelper(this)
				.add("id",    id)
				.add("name",  name)
				.add("price", price)
				.omitNullValues()
				.toString();

	}
}
