package com.dzone.albanoj2.examples.patterns.strategy;

import java.util.Optional;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dzone.albanoj2.examples.patterns.strategy.di.DependencyInjectedBill;
import com.dzone.albanoj2.examples.patterns.strategy.reflection.RunTimeReflectionPaymentMethodFactory;
import com.dzone.albanoj2.examples.patterns.strategy.reflection.StaticReflectionPaymentMethodFactory;

public class Application {
	
	private static final int PAYMENT_TYPE_INDEX = 0;

	public static void main (String[] args) {
		
		Bill bill = new Bill();
		
		bill.addLineItem(new LineItem("Milk", 200));
		bill.addLineItem(new LineItem("Bread", 150));
		
		bill.pay(PaymentMethodFactory.getPaymentMethod(args[PAYMENT_TYPE_INDEX]));
		
		Optional<PaymentMethod> runTimePaymentMethod = RunTimeReflectionPaymentMethodFactory.getPaymentMethod("com.dzone.albanoj2.examples.patterns.strategy.Cash");
		runTimePaymentMethod.ifPresent(method -> bill.pay(method));
		
		Optional<PaymentMethod> staticPaymentMethod = StaticReflectionPaymentMethodFactory.getPaymentMethod();
		staticPaymentMethod.ifPresent(method -> bill.pay(method));
		
		BeanFactory factory = (BeanFactory) new ClassPathXmlApplicationContext("META-INF/beans.xml");
        DependencyInjectedBill diBill = (DependencyInjectedBill) factory.getBean("com.dzone.albanoj2.examples.patterns.strategy.di.DependencyInjectedBill");
		
        diBill.addLineItem(new LineItem("Eggs", 350));
        diBill.addLineItem(new LineItem("Cheese", 150));
        diBill.pay();
	}
}
