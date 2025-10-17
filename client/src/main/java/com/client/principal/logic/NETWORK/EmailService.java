package com.client.principal.logic.NETWORK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.client.principal.logic.data.network.PaymentEP;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSubscriptionEmail(String toEmail, PaymentEP payment) {
        String subject = "Confirmación de compra de suscripción";
        String body = String.format(
                "Gracias por tu compra.\n\n" +
                        "Detalles de la suscripción:\n" +
                        "- ID de pago: %s\n" +
                        "- Método de pago: %s\n" +
                        "- Monto: $%.2f\n" +
                        "- Inicio: %s\n" +
                        "- Fin: %s\n" +
                        "- ID de suscripción: %s\n",
                payment.getId(),
                payment.getPaymentMethod(),
                payment.getAmount(),
                payment.getStartSubscription(),
                payment.getEndSubscription(),
                payment.getSubscriptionName());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
