package cn.dubby.encrypt.decrypt.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class KeyPairGenerator_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);

        KeyPair keyPair = keyPairGenerator.genKeyPair();

        System.out.println(keyPair);
        System.out.println(keyPair.getPublic());
        System.out.println(keyPair.getPrivate());
    }

}
