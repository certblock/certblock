package i05.a507.certblock.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Predicate;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes128 {
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;
    private IvParameterSpec ivp;
    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;
    private static final String INSTANCE_TYPE = "AES/CBC/PKCS5Padding";

    public Aes128(final String key) {
        validation(key);
        final String finalKey = key.substring(0, 16);
        try {
            byte[] keyBytes = finalKey.getBytes(ENCODING_TYPE);
            secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            cipher = Cipher.getInstance(INSTANCE_TYPE);
            ivp = new IvParameterSpec(keyBytes);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // 암호화
    public String encrypt(final String str) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivp);
        byte[] encrypted = cipher.doFinal(str.getBytes(ENCODING_TYPE));
        return new String(java.util.Base64.getEncoder().encode(encrypted), ENCODING_TYPE);
    }

    // 복호화
    public String decrypt(final String str) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivp);
        byte[] decrypted = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        return new String(cipher.doFinal(decrypted), ENCODING_TYPE);
    }

    private void validation(final String key) {
        Optional.ofNullable(key)
                .filter(Predicate.not(String::isBlank))
                .filter(Predicate.not(s -> s.length() < 16))
                .orElseThrow(IllegalArgumentException::new);
    }
}