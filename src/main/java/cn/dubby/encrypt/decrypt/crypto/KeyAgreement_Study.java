package cn.dubby.encrypt.decrypt.crypto;

import cn.dubby.encrypt.decrypt.util.ByteUtil;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class KeyAgreement_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");

        KeyPair keyPair1 = keyPairGenerator.generateKeyPair();
        KeyPair keyPair2 = keyPairGenerator.generateKeyPair();

        KeyAgreement keyAgreement = KeyAgreement.getInstance(keyPairGenerator.getAlgorithm());
        keyAgreement.init(keyPair2.getPrivate());
        keyAgreement.doPhase(keyPair1.getPublic(), true);

        SecretKey secretKey = keyAgreement.generateSecret("DES");
        System.out.println(ByteUtil.byteToHex(secretKey.getEncoded()));
    }

}
