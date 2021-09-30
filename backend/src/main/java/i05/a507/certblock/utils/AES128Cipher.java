package i05.a507.certblock.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class AES128Cipher {
    private String ips;
    private Key keySpec;
    private final String secretKey; // 32bit

//    private static volatile AES128Cipher INSTANCE;

//    final static String secretKey = "jmlim12345bbbbbaaaaa123456789066"; //32bit
    private String IV = ""; //16bit

//    public static AES128Cipher getInstance() {
//        if (INSTANCE == null) {
//            synchronized (AES128Cipher.class) {
//                if (INSTANCE == null)
//                    INSTANCE = new AES128Cipher();
//            }
//        }
//        return INSTANCE;
//    }

    public AES128Cipher(String secretKey) {
        this.secretKey = secretKey;
        this.IV = secretKey.substring(0, 16);
    }

    //암호화
    public String encrypt(String str)
            throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] keyData = secretKey.getBytes();

        SecretKey secureKey = new SecretKeySpec(keyData, "AES");

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));

        byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
        String enStr = new String(Base64.encodeBase64(encrypted));

        return enStr;
    }

    //복호화
//    public String AES_Decode(String str)
    public String decrypt(String str)
            throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] keyData = secretKey.getBytes();
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));

        byte[] byteStr = Base64.decodeBase64(str.getBytes());

        return new String(c.doFinal(byteStr), StandardCharsets.UTF_8);
    }
}