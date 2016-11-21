package io.codelirium.shoppingcart.domain.payment;


public interface PaymentMethod {

	boolean checkout(final double amount);

}
