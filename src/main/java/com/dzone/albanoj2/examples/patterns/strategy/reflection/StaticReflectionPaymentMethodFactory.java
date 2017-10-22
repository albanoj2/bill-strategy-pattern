package com.dzone.albanoj2.examples.patterns.strategy.reflection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import com.dzone.albanoj2.examples.patterns.strategy.PaymentMethod;

public class StaticReflectionPaymentMethodFactory {

	public static Optional<PaymentMethod> getPaymentMethod() {

		try {
			Optional<String> className = getPaymentMethodClassName();
			
			if (className.isPresent()) {
				Class<?> clazz = Class.forName(className.get());
				PaymentMethod method = (PaymentMethod) clazz.newInstance();
				return Optional.of(method);
			}
			else {
				return Optional.empty();
			}
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException e) {
			e.printStackTrace();
			return Optional.empty();
		}
		
	}
	
	private static Optional<String> getPaymentMethodClassName() {
		
		try {
			Properties paymentConfig = new Properties();
			InputStream configFile = StaticReflectionPaymentMethodFactory.class.getResourceAsStream("/payment.config.properties");
			paymentConfig.load(configFile);
			
			return Optional.of(paymentConfig.getProperty("paymentMethod.className"));
		}
		catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
