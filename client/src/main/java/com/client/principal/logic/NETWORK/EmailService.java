package com.client.principal.logic.NETWORK;

import org.springframework.stereotype.Service;

import com.client.principal.logic.data.network.PaymentEP;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    private final RestTemplate restTemplate = new RestTemplate();

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

        // Construir request para Brevo
        Map<String, Object> payload = new HashMap<>();
        payload.put("sender", Map.of("email", senderEmail));
        payload.put("to", new Map[] { Map.of("email", toEmail) });
        payload.put("subject", subject);
        payload.put("textContent", body);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    "https://api.brevo.com/v3/smtp/email",
                    request,
                    String.class);
            System.out.println("Email enviado: " + response.getStatusCode());
        } catch (Exception e) {
            System.err.println("No se pudo enviar el email a " + toEmail + ": " + e.getMessage());
        }
    }
}
