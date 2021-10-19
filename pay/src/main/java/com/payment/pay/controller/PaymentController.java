package com.payment.pay.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.pay.client.StripeClient;
import com.payment.pay.exception.InvalidAmountException;
import com.payment.pay.model.ClientInput;
import com.payment.pay.response.PaymentResponse;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import java.nio.file.Paths;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.port;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private StripeClient stripeClient;
    private static Gson gson = new Gson();
    
    @Value("${isDummyEnabled}")
    private String isDummyEnabled;
    
    @Value("${validator.whitelist.regex}")
    private String regex;

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public ResponseEntity<String> chargeCard(@RequestParam(value = "token", defaultValue="tok_mastercard") String token, 
    		@RequestParam(value = "amount", defaultValue = "0") String amount) throws Exception {
    	double amountindecimal=0.0;
    	try {
    		amountindecimal=Double.parseDouble(amount);
    	}catch(NumberFormatException e){
           throw new Exception("Invalid Amount:" +amount);
        }
        System.out.println("amount="+amount);
        System.out.println("isDummyEnabled?"+isDummyEnabled);
        if(Boolean.parseBoolean(isDummyEnabled))
        	return new ResponseEntity<String>(
        			stripeClient.chargeNewCardDummy(token, amountindecimal).toJson(), HttpStatus.OK);
         return new ResponseEntity<String>(stripeClient.chargeNewCard(token, amountindecimal).toJson(), HttpStatus.OK);
	//return "payment success"; 
        
    }
    
    @PostMapping("/invalidate/session")
    public String destroySession(HttpServletRequest request) {
        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
        request.getSession().invalidate();
        return "redirect:/home";
    }
    
    static class CreatePaymentResponse {
        private String clientSecret;

        public CreatePaymentResponse(String clientSecret) {
          this.clientSecret = clientSecret;
        }
      }
    
    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody ClientInput clientInput) 
    		throws Exception { 
    	//CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
    	PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
    			.setCurrency(clientInput.getCurrency())
    			.setAmount((long)clientInput.getItems().get(0).getAmount())
    			.build();
    	// Create a PaymentIntent with the order amount and currency
    	PaymentIntent intent = PaymentIntent.create(createParams);

    	CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());
    	return gson.toJson(paymentResponse);
    }
    
    
    @ExceptionHandler({ InvalidAmountException.class })
    public void handleException() {
        //
    }
    
}
