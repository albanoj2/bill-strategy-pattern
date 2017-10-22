package com.dzone.albanoj2.examples.patterns.strategy.reflection;

import java.util.Optional;

import com.dzone.albanoj2.examples.patterns.strategy.PaymentMethod;

public class RunTimeReflectionPaymentMethodFactory {

	public static Optional<PaymentMethod> getPaymentMethod(String qualifiedName) {

		try {
			Class<?> clazz = Class.forName(qualifiedName);
			PaymentMethod method = (PaymentMethod) clazz.newInstance();
			return Optional.of(method);
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException e) {
			return Optional.empty();
		}
		
	}
}
