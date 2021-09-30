package i05.a507.certblock.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {
    public static String generate(String str) throws NoSuchAlgorithmException {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(str.getBytes());

      return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
      StringBuilder builder = new StringBuilder();
      for (byte b : bytes) {
        builder.append(String.format("%02x", b));
      }
      return builder.toString();
    }
}
