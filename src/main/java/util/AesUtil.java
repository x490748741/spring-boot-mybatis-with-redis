package util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AesUtil {
    public static void main(String[] args) {
        String str = "aaa";
        String privateKey ="ccc";
        System.out.println("初始str:"+str);
        System.out.println("aes密钥:"+privateKey);

        String strmd5 = encrypt16(str);
        System.out.println("md5加密16位:"+strmd5);


        String enaes = AES.encrypt(strmd5,privateKey);
        String deaes = AES.decrypt(enaes,privateKey);


        System.out.println("aes加密:"+enaes);
        System.out.println("aes解密:"+deaes);
    }


    /**
     * encrypt32
     *
     * @param encryptStr
     * @return String
     */
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    /**
     * encrypt16
     *
     * @param encryptStr
     * @return String
     */
    public static String encrypt16(String encryptStr) {
        return encrypt32(encryptStr).substring(8, 24);
    }


    public static class AES{
//AES是一种加密算法，这是老师给的代码，直接调用就好，包括了setkey（貌似是hash方程，可以直接代替上面地hash），encryption（加密），decryption（解密）

        private static SecretKeySpec secretKey;
        private static byte[] key;
        private static final String initVector = "L4%6s9$7*1nj0oPl"; //this mist be 16 bytes (16 characters)



        public static void setKey(String myKey)
        {
            MessageDigest sha = null;
            try {
                key = myKey.getBytes("UTF-8");
                sha = MessageDigest.getInstance("SHA-1");//sha1就是hash方程
                key = sha.digest(key);
                key = Arrays.copyOf(key, 16);
                secretKey = new SecretKeySpec(key, "AES");
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        public static String encrypt(String strToEncrypt, String secret)
        {
            try
            {
                setKey(secret);
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
                return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
            }
            catch (Exception e)
            {
                System.out.println("Error while encrypting: " + e.toString());
            }
            return null;
        }

        public static String decrypt(String strToDecrypt, String secret)
        {
            try
            {
                setKey(secret);
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
                return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            }
            catch (Exception e)
            {
                System.out.println("Error while decrypting: " + e.toString());
            }
            return null;
        }

    }
}
