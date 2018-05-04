package cn.dubby.encrypt.decrypt.crypto;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class Cipher_Study {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, UnsupportedEncodingException, BadPaddingException {
        String algorithm = "DES", charSet = "UTF-8";

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance(algorithm);

        //包装密钥
        cipher.init(Cipher.WRAP_MODE, secretKey);
        byte[] keyByte = cipher.wrap(secretKey);

        //解包密钥
        cipher.init(Cipher.UNWRAP_MODE, secretKey);
        Key key = cipher.unwrap(keyByte, algorithm, Cipher.SECRET_KEY);
        System.out.println(secretKey.equals(key));

        //加密数据
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] input = cipher.doFinal("http://www.dubby.cn 这是杨正的个人博客".getBytes(charSet));

        //解密数据
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] output = cipher.doFinal(input);
        System.out.println(new String(output, charSet));
    }

}
