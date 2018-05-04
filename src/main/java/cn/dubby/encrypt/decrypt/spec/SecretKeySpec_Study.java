package cn.dubby.encrypt.decrypt.spec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class SecretKeySpec_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String algorithm = "RC2";
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] key = secretKey.getEncoded();

        SecretKey secretKey1 = new SecretKeySpec(key, algorithm);

        System.out.println(secretKey.equals(secretKey1));
    }

}
