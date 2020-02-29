package jestx;

import java.security.*;

public abstract class Hash {
    /**
     * 
     */
    public static String asSha256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes());
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                buffer.append('0');
            buffer.append(hex);
        }
        return buffer.toString();
    }
}