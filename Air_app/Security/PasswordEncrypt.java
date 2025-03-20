package com.air_app.security;


//package com.air_app.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncrypt {
	
	private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "MySuperSecretKey"; // Must be 16 characters for AES-128
    private static final String IV = "1234567890123456"; // 16-byte IV for CBC mode

    private static SecretKeySpec getSecretKey(String key) throws Exception {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        
        // Ensure the key is exactly 16 bytes
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        byte[] shortKey = new byte[16];
        //System.arraycopy(keyBytes, 0, shortKey, 0, 16);

        return new SecretKeySpec(shortKey, "AES");
    }
    
    // Encrypt password
    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec secretKey = getSecretKey(SECRET_KEY );
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    // Decrypt password
    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec secretKey = getSecretKey(SECRET_KEY);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}