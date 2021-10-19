package com.payment.pay.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;



import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {

    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_uTAM1qndRDbiJRowe8dJf6x9";
    }
//If you want to save user’s credit card on Stripe side then you should create customer using the following method:
    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }

    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }

    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    //
    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
    
    public PaymentIntent createPaymentIntent(int amount, String paymentMethodId) throws StripeException {
    Map<String, Object> createPaymentIntentParams = new HashMap<String, Object>();
    createPaymentIntentParams.put("currency", "inr");
    createPaymentIntentParams.put("amount", 1099);
    createPaymentIntentParams.put("confirm", true);
    createPaymentIntentParams.put("confirmation_method", "manual");
    createPaymentIntentParams.put("payment_method", paymentMethodId);
    return PaymentIntent.create(createPaymentIntentParams);
    }
    
	public Charge chargeNewCardDummy(String token, double amountindecimal) {
		return new Charge();
	}


    
    
    
}
