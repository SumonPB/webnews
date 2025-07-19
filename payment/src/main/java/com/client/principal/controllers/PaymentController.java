package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.logic.PaymentUC;
import com.client.principal.logic.DAO.BillDAO;
import com.client.principal.logic.data.PaymentUI;
import com.client.principal.logic.data.newtwork.SubscriptionEP;

@RestController
public class PaymentController {
    @Autowired
    private PaymentUC paymentUC;

    @GetMapping("/CreatePayment")
    public PaymentUI createPayment(
            @RequestParam("clientEmail") String clientEmail,
            @RequestParam("subscriptionname") String subscriptionName,
            @RequestParam("paymentmethod") String paymentMethod) {

        return paymentUC.processPayment(clientEmail, subscriptionName, paymentMethod);
    }

    @GetMapping("/GetSubscriptionDetails")
    public SubscriptionEP getSubscriptionDetails(@RequestParam("name") String name) {
        return paymentUC.getSubscriptionDetails(name);
    }

    @GetMapping("GetAllPayments")
    public List<PaymentUI> getMethodName() {
        return paymentUC.getAllPayments();
    }

    @GetMapping("GetBillById")
    public PaymentUI getBillById(
            @RequestParam("billId") String billId) {
        return paymentUC.getBillById(billId);
    }

}
