package com.client.principal.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.client.principal.logic.DAO.BillsDAO;
import com.client.principal.logic.Network.PaymentUI;
import com.client.principal.logic.Network.SubscriptionUI;
import com.client.principal.logic.data.newtwork.PaymentEP;
import com.client.principal.logic.data.newtwork.SubscriptionEP;

@Controller
public class PaymentsController {
    @Autowired
    PaymentUI paymentUI;
    @Autowired
    SubscriptionUI subscriptionUI;

    @GetMapping("/getAllBills")
    @ResponseBody
    public List<BillsDAO> getAllBils() {
        List<PaymentEP> paymentList = paymentUI.getAllBill();
        List<BillsDAO> bills = new ArrayList<>();
        for (PaymentEP pay : paymentList) {
            SubscriptionEP ns = subscriptionUI.findSubscriptionById(pay.getSubscriptionId());
            bills.add(BillsDAO.builder()
                    .id(pay.getId())
                    .paymentMethod(pay.getPaymentMethod())
                    .amount(pay.getAmount())
                    .startSubscription(pay.getStartSubscription())
                    .endSubscription(pay.getEndSubscription())
                    .userId(pay.getUserId())
                    .subscriptionName(ns.getName())
                    .build());
        }

        return bills;
    }

    @GetMapping("/admin/stadistics")
    public String showStadistics() {
        return "stadistics";
    }

}
