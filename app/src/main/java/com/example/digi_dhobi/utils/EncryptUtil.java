package com.example.digi_dhobi.utils;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class EncryptUtil {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
    private final String bijay11 = "Xa8znj";
    private final String bijay12 = "Gl1rsE";
    private final String bijay13 = "M2TQVF4zRb";
    private final String bijay14 = "5yGvdX3mpV";
    private final String bijay21 = "kt5orsmH";
    private final String bijay22 = "DxNA6hq7";
    private final String bijay23 = "o2SLll08";
    private final String bijay24 = "MxBxnea3PPTX";
    private final String bijay25 = "96Ym2AowmT";
    private final String bijay26 = "yD64QNoxM";
    private final String bijay27 = "oG84BDv/E";

    public EncryptUtil() {
        try {
            myEncryptionKey = "BijayOnlineProjectFireBijayOnline";
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            ks = new DESedeKeySpec(arrayBytes);
            skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
            key = skf.generateSecret(ks);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }

    private String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = android.util.Base64.decode(encryptedString, android.util.Base64.DEFAULT);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

    public String getBijay1() {
        return this.decrypt(bijay11+bijay12+bijay13+bijay14);
    }

    public String getBijay2() {
        return this.decrypt(bijay21+bijay22+bijay23+bijay24+bijay25+bijay26+bijay27);
    }
}