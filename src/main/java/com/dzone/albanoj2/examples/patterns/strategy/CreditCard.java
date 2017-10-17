package com.dzone.albanoj2.examples.patterns.strategy;

public class CreditCard extends Card {
	
	public CreditCard(String nameOnCard, String number, String cvv, String expirationDate) {
		super(nameOnCard, number, cvv, expirationDate);
	}

	@Override
	protected String getType() {
		return "credit";
	}

	@Override
	protected void executeTransaction(int cents) {
		// Contact credit holder to make transaction
	}
}