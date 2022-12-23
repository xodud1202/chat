package com.xodud1202.chat.support.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
	public static String encryptSha512(String rawPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(rawPassword.getBytes());
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException var2) {
            var2.printStackTrace();
            return "";
        }
    }
}
