package cn.dubby.encrypt.decrypt.spec;

import cn.dubby.encrypt.decrypt.util.ByteUtil;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class DESedeKeySpec_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        String algorithm = "DESede";
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecretKey secretKey = keyGenerator.generateKey();

        byte[] key = secretKey.getEncoded();

        DESedeKeySpec desKeySpec = new DESedeKeySpec(key);

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
        SecretKey secretKey1 = secretKeyFactory.generateSecret(desKeySpec);

        System.out.println(secretKey.equals(secretKey1));
        System.out.println(ByteUtil.byteToHex(secretKey.getEncoded()));
        System.out.println(ByteUtil.byteToHex(secretKey1.getEncoded()));
    }

}
