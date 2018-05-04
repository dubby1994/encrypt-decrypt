package cn.dubby.encrypt.decrypt.crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class SecretKetFactory_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        //实例化
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey2 =  keyFactory.generateSecret(desKeySpec);

        System.out.println(secretKey.equals(secretKey2));
    }

}
