package com.client.principal.logic.Validations_Encriptations;

import java.util.regex.Pattern;

public class EmailPaswordVal {
    public static boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        String passwordRegex = "^[A-Za-z0-9]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }
}
