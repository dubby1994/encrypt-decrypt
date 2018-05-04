package cn.dubby.encrypt.decrypt.spec;

import cn.dubby.encrypt.decrypt.util.ByteUtil;

import javax.crypto.KeyGenerator;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * EncodedKeySpec用来表示公钥、私钥
 * 子类:X509EncodedKeySpec——公钥
 * 子类:PKCS8EncodedKeySpec——私钥
 * Created by yangzheng03 on 2018/5/4.
 */
public class EncodedKeySpec_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String algorithm = "DSA";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(1024);

        KeyPair keyPair = keyPairGenerator.genKeyPair();

        //获得公钥
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();

        //获得私钥
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        System.out.println(publicKey.equals(keyPair.getPublic()));
        System.out.println(ByteUtil.byteToHex(publicKey.getEncoded()));
        System.out.println(ByteUtil.byteToHex(keyPair.getPublic().getEncoded()));

        System.out.println(privateKey.equals(keyPair.getPrivate()));
        System.out.println(ByteUtil.byteToHex(privateKey.getEncoded()));
        System.out.println(ByteUtil.byteToHex(keyPair.getPrivate().getEncoded()));
    }

}
