package com.dzone.albanoj2.examples.patterns.strategy;

public class Cash implements PaymentMethod {

	@Override
	public void pay(int cents) {
		System.out.println("Payed " + cents + " cents using cash");
	}
}
