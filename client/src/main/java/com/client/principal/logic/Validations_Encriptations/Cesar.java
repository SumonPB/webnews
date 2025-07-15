package com.client.principal.logic.Validations_Encriptations;

import org.springframework.stereotype.Service;

@Service
public class Cesar implements Encriptation {
    private static final int SHIFT = 2;

    @Override
    public String encrypt(String data) {
        return shift(data, SHIFT);
    }

    public String decrypt(String data) {
        return shift(data, 26 - SHIFT);
    }

    private String shift(String data, int shift) {
        if (data == null)
            return null;

        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append((char) ('a' + (c - 'a' + shift) % 26));
            } else if (c >= 'A' && c <= 'Z') {
                sb.append((char) ('A' + (c - 'A' + shift) % 26));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
