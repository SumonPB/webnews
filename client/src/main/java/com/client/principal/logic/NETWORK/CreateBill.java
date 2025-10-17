package com.client.principal.logic.NETWORK;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.network.PaymentEP;

@FeignClient(name = "payment")
public interface CreateBill {
        @GetMapping("/CreatePayment")
        public PaymentEP createPayment(
                        @RequestParam("clientEmail") String clientEmail,
                        @RequestParam("subscriptionname") String subscriptionName,
                        @RequestParam("paymentmethod") String paymentMethod);

        @GetMapping("GetBillById")
        public PaymentEP getBillById(
                        @RequestParam("billId") String billId);
}
