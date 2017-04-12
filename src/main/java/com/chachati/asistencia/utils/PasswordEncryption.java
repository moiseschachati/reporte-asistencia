package com.chachati.asistencia.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {

    public static Boolean authenticate(String attemptedPassword, String encryptedPassword) {
        return BCrypt.checkpw(attemptedPassword, encryptedPassword);
    }

    public static String hashPassword(String password) {
        // Hash a password for the first time
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
