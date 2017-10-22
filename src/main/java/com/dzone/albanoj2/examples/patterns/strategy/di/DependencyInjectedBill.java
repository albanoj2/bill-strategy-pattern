package com.dzone.albanoj2.examples.patterns.strategy.di;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dzone.albanoj2.examples.patterns.strategy.Bill;
import com.dzone.albanoj2.examples.patterns.strategy.LineItem;
import com.dzone.albanoj2.examples.patterns.strategy.PaymentMethod;

public class DependencyInjectedBill extends Bill {

	@Autowired
	private PaymentMethod paymentMethod;
	
	private List<LineItem> lineItems = new ArrayList<>();
	
	public void addLineItem(LineItem lineItem) {
		lineItems.add(lineItem);
	}
	
	public void removeLineItem(LineItem lineItem) {
		lineItems.remove(lineItem);
	}
	
	public int getCostInCents() {
		return lineItems.stream().mapToInt(item -> item.getCostInCents()).sum();
	}
	
	public void pay() {
		paymentMethod.pay(getCostInCents());
	}
}
