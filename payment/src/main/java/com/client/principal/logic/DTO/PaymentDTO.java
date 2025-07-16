package com.client.principal.logic.DTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.concurrent.Flow.Subscription;

import com.client.principal.data.entities.Payment;
import com.client.principal.logic.DAO.BillDAO;
import com.client.principal.logic.data.PaymentUI;
import com.client.principal.logic.data.newtwork.SubscriptionEP;
import com.client.principal.logic.data.newtwork.UserEP;

public class PaymentDTO {
        public static PaymentUI toPaymentUI(Payment payment) {
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
                                .subscriptionId(payment.getSubscriptionId())
                                .build();
        }

        public static Payment toPaymentEntity(PaymentUI paymentUI) {
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
                                .subscriptionId(paymentUI.getSubscriptionId())
                                .build();
        }

        public static BillDAO createBill(Payment payment, SubscriptionEP subscriptionEP, UserEP userEP) {

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
