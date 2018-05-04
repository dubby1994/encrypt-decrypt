package cn.dubby.encrypt.decrypt.crypto;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class SealedObject_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException {
        String message = "http://www.dubby.cn", algorithm = "DES";

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher1 = Cipher.getInstance(algorithm);
        cipher1.init(Cipher.ENCRYPT_MODE, secretKey);

        SealedObject sealedObject = new SealedObject(message, cipher1);

        Cipher cipher2 = Cipher.getInstance(algorithm);
        cipher2.init(Cipher.DECRYPT_MODE, secretKey);

        String result = (String) sealedObject.getObject(cipher2);
        System.out.println(result);
    }

}
