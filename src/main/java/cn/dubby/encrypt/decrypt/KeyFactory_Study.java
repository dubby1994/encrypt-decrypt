package cn.dubby.encrypt.decrypt;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class KeyFactory_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //实例化KeyPairGenerator对象，并制定RSA算法
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);

        //生成KeyPair对象
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        //获得私钥秘钥字节数组，生产中，这个字节数组应该以其他形式保存或传递
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        //构建密钥规范
        PKCS8EncodedKeySpec privatePkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //生成私钥
        Key privateKey = keyFactory.generatePrivate(privatePkcs8EncodedKeySpec);

        System.out.println(privateKey.equals(keyPair.getPrivate()));
    }

}
