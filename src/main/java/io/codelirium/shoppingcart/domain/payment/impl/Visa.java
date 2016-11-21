package io.codelirium.shoppingcart.domain.payment.impl;

import io.codelirium.shoppingcart.domain.payment.PaymentMethodDetails;
import io.codelirium.shoppingcart.domain.payment.PaymentMethod;
import java.util.Date;


public class Visa extends PaymentMethodDetails implements PaymentMethod {

	public Visa(final String name, final String cardNumber, final Date expiry) {

		super(name, cardNumber, expiry);

	}


	@Override
	public boolean checkout(final double amount) {

		// TODO: Implement Visa logic.

		return true;

	}
}
