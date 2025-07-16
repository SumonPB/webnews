package com.client.principal.logic.DTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

import com.client.principal.data.entities.Payment;
import com.client.principal.logic.data.PaymentUI;

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
                        paymentUI.getStartSubscription().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .endSubscription(
                        paymentUI.getEndSubscription().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .userId(paymentUI.getUserId())
                .subscriptionId(paymentUI.getSubscriptionId())
                .build();
    }

}
