package com.client.principal.logic.DTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.client.principal.logic.DAO.paymentDao;
import com.client.principal.logic.NETWORK.GetSubscription;
import com.client.principal.logic.data.network.PaymentEP;

public class PaymentDTO {

        public static List<paymentDao> toPaymentDao(List<PaymentEP> paymentEPList) {
                return paymentEPList.stream()
                                .map(paymentEP -> paymentDao.builder()
                                                .id(paymentEP.getId())
                                                .amount(paymentEP.getAmount())
                                                .paymentMethod(paymentEP.getPaymentMethod())
                                                .startSubscription(paymentEP.getStartSubscription())
                                                .endSubscription(paymentEP.getEndSubscription())
                                                .subscriptionName(paymentEP.getSubscriptionName())
                                                .build())
                                .toList();
        }

}
