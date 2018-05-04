package cn.dubby.encrypt.decrypt.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class SecureRandom_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");

        keyGenerator.init(secureRandom);

        SecretKey secretKey = keyGenerator.generateKey();
    }

}
