package io.codelirium.shoppingcart.core;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import io.codelirium.shoppingcart.domain.payment.PaymentMethod;
import io.codelirium.shoppingcart.domain.product.ShopProduct;
import org.springframework.util.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ShoppingCart implements ShoppingCartVisitor {

	private List<ShopProduct> items;
	private Lock              itemsLock;
	private boolean           isCheckedOut;


	public ShoppingCart() {

		items        = Lists.newLinkedList();
		itemsLock    = new ReentrantLock();
		isCheckedOut = false;

	}


	@Override
	public void addProduct(final ShopProduct shopProduct) {

		Assert.notNull(shopProduct, "The shop product cannot be null.");


		itemsLock.lock();


		try {

			if (!isCheckedOut) {

				items.add(shopProduct);

			}

		} finally {

			itemsLock.unlock();

		}
	}

	@Override
	public void removeProduct(final ShopProduct shopProduct) {

		Assert.notNull(shopProduct, "The shop product cannot be null.");


		itemsLock.lock();


		try {

			if (!isCheckedOut) {

				items.remove(shopProduct);

			}

		} finally {

			itemsLock.unlock();

		}
	}

	public double calculateTotalCost() {

		itemsLock.lock();


		try {

			return items.parallelStream()
									.mapToDouble(ShopProduct::getPrice)
									.reduce(0, Double::sum);

		} finally {

			itemsLock.unlock();

		}
	}

	public boolean checkout(final PaymentMethod paymentMethod) {

		Assert.notNull(paymentMethod, "The payment method cannot be null.");


		itemsLock.lock();


		try {

			if (!isCheckedOut && paymentMethod.checkout(calculateTotalCost())) {

				isCheckedOut = true;

			}


			return isCheckedOut;

		} finally {

			itemsLock.unlock();

		}
	}


	public List<ShopProduct> getCartProducts() {

		return Lists.newArrayList(items);

	}

	public int getCartSize() {

		return items.size();

	}

	public boolean isCartCheckedOut() {

		return isCheckedOut;

	}


	@Override
	public String toString() {

		return MoreObjects.toStringHelper(this)
				.add("items", Arrays.toString(items.toArray()))
				.add("isCheckedOut", isCheckedOut)
				.omitNullValues()
				.toString();

	}
}
