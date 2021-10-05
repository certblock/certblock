package i05.a507.certblock.utils;


import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;
//import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class Base64Encoder {
    public static String encodeFileToBase64Binary(File imageFile, String fileExtName) throws Exception {
        FileInputStream inputStream = null;
        ByteArrayOutputStream byteOutStream = null;
        try {
            inputStream = new FileInputStream(imageFile);
            byteOutStream = new ByteArrayOutputStream();

            int len = 0;
            byte[] buf = new byte[1024];
            while( (len = inputStream.read( buf )) != -1 ) {
                byteOutStream.write(buf, 0, len);
            }

            byte[] fileArray = byteOutStream.toByteArray();
            String encoded = new String(Base64.encodeBase64(fileArray));
            return "data:image/" + fileExtName +";base64, " + encoded;
        }
        catch( IOException e) {
            e.printStackTrace();
        }
        finally {
            inputStream.close();
            byteOutStream.close();
        }
        return "";
    }

//    public static String encodeFileToBase64Binary(File file) {
//        String encodedFile = null;
//        try {
//            FileInputStream fileInputStreamReader = new FileInputStream(file);
//            byte[] bytes = new byte[(int)file.length()];
//            fileInputStreamReader.read(bytes);
////            encodedFile = Base64.getEncoder().encodeToString(bytes);
//            encodedFile = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return encodedFile;
//    }
}
