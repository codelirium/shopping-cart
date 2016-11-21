package io.codelirium.shoppingcart.domain.payment;

import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class PaymentMethodDetails implements Serializable {

	private static final long serialVersionUID = 3446526921316205100L;


	private final String name;
	private final String number;
	private final Date   expiry;


	public PaymentMethodDetails(final String name, final String number, final Date expiry) {

		this.name   = name;
		this.number = number;
		this.expiry = expiry;

	}


	public String getName() {

		return name;

	}

	public String getNumber() {

		return number;

	}

	public Date getExpiry() {

		return expiry;

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
				.add("name",   name)
				.add("number", number)
				.add("expiry", expiry)
				.omitNullValues()
				.toString();

	}
}
