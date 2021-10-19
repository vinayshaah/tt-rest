package com.payment.pay.response;

import java.net.http.HttpResponse;
import java.util.Map;

import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;

public class PaymentResponse {

	String responseData;
	Charge charge;
	public PaymentResponse(Charge charge) {
		// TODO Auto-generated constructor stub
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	PaymentIntent intent;
	
	public void setCharge(Charge charge) {
		this.charge = charge;
	}
}
	
/*
 * public PaymentResponse generatePaymentResponse( ) {
 * 
 * if (intent.getStatus().equals("requires_action") &&
 * intent.getNextAction().getType().equals("use_stripe_sdk")) {
 * responseData="Payment requires_action"; } else if
 * (intent.getStatus().equals("succeeded")) { responseData="Payment success"; }
 * else { // invalid status responseData="Error-Invalid status"; return
 * response; } return response; } }
 */
