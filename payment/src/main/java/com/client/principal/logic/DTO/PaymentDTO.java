package com.client.principal.logic.DTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.concurrent.Flow.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.Payment;
import com.client.principal.logic.DAO.BillDAO;
import com.client.principal.logic.Network.GetSubscription;
import com.client.principal.logic.data.PaymentUI;
import com.client.principal.logic.data.newtwork.SubscriptionEP;
import com.client.principal.logic.data.newtwork.UserEP;

@Service
public class PaymentDTO {
        @Autowired
        GetSubscription getSubscription;

        public PaymentUI toPaymentUI(Payment payment) {
                return PaymentUI.builder()
                                .id(payment.getId())
                                .paymentMethod(payment.getPaymentMethod())
                                .amount(payment.getAmount())
                                .startSubscription(LocalDateTime.ofInstant(
                                                Instant.ofEpochMilli(payment.getStartSubscription()),
                                                TimeZone.getDefault().toZoneId()))
                                .endSubscription(LocalDateTime.ofInstant(
                                                Instant.ofEpochMilli(payment.getEndSubscription()),
                                                TimeZone.getDefault().toZoneId()))
                                .userId(payment.getUserId())
                                .subscriptionName(getSubscription.findSubscriptionById(payment.getSubscriptionId())
                                                .getName())
                                .build();
        }

        public Payment toPaymentEntity(PaymentUI paymentUI) {
                return Payment.builder()
                                .id(paymentUI.getId())
                                .paymentMethod(paymentUI.getPaymentMethod())
                                .amount(paymentUI.getAmount())
                                .startSubscription(
                                                paymentUI.getStartSubscription().atZone(ZoneId.systemDefault())
                                                                .toInstant().toEpochMilli())
                                .endSubscription(
                                                paymentUI.getEndSubscription().atZone(ZoneId.systemDefault())
                                                                .toInstant().toEpochMilli())
                                .userId(paymentUI.getUserId())
                                .subscriptionId(getSubscription
                                                .GetSubscriptionByName(paymentUI.getSubscriptionName().toString())
                                                .getId())
                                .build();
        }

        public BillDAO createBill(Payment payment, SubscriptionEP subscriptionEP, UserEP userEP) {

                return BillDAO.builder()
                                .billID(toPaymentUI(payment).getId())
                                .clientName(userEP.getName())
                                .subscriptionName(subscriptionEP.getName())
                                .startSubscription(toPaymentUI(payment).getStartSubscription())
                                .endSubscription(toPaymentUI(payment).getEndSubscription())
                                .payMethod(toPaymentUI(payment).getPaymentMethod())
                                .amount(toPaymentUI(payment).getAmount())
                                .build();
        }
}
