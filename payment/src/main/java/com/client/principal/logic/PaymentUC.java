package com.client.principal.logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.Payment;
import com.client.principal.data.repositorys.PaymentRepository;
import com.client.principal.logic.DAO.BillDAO;
import com.client.principal.logic.DTO.PaymentDTO;
import com.client.principal.logic.Network.GetClient;
import com.client.principal.logic.Network.GetSubscription;
import com.client.principal.logic.data.PaymentUI;
import com.client.principal.logic.data.newtwork.SubscriptionEP;
import com.client.principal.logic.data.newtwork.UserEP;
import com.client.principal.logic.data.newtwork.subscriptionTypes;

@Service
public class PaymentUC {
    @Autowired
    GetSubscription getSubscription;
    @Autowired
    GetClient getClient;
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private PaymentDTO paymentDTO;

    public SubscriptionEP getSubscriptionDetails(String name) {
        return getSubscription.GetSubscriptionByName(name);
    }

    public UserEP getClientDetails(String clientEmail) {
        return getClient.getClientByEmail(clientEmail);
    }

    public PaymentUI processPayment(String clientEmail, String subscriptionName, String paymentMethod) {
        UserEP client = getClient.getClientByEmail(clientEmail);
        SubscriptionEP subscription = getSubscription.GetSubscriptionByName(subscriptionName);
        if (client == null || subscription == null) {
            return null;
        }
        PaymentUI paymentUI = PaymentUI.builder()
                .paymentMethod(paymentMethod)
                .amount(subscription.getName().equals(subscriptionTypes.FREE) ? 0.0
                        : subscription.getName().equals(subscriptionTypes.TITULAR) ? 1.99
                                : subscription.getName().equals(subscriptionTypes.REDACCIONPlus) ? 3.99 : 5.99)
                .userId(client.getId())
                .startSubscription(LocalDateTime.now())
                .endSubscription(LocalDateTime.now().plusDays(30))
                .subscriptionName(subscription.getName())
                .build();

        Payment payment = paymentRepository.save(paymentDTO.toPaymentEntity(paymentUI));

        return paymentDTO.toPaymentUI(payment);

    }

    public List<PaymentUI> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentDTO::toPaymentUI)
                .collect(Collectors.toList());
    }

    public PaymentUI getBillById(String id) {

        return paymentDTO.toPaymentUI(paymentRepository.getBillById(id));
    }

}
