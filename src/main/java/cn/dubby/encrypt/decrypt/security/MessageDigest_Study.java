package cn.dubby.encrypt.decrypt.security;

import cn.dubby.encrypt.decrypt.util.ByteUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class MessageDigest_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final String message = "hello world!", charSet = "UTF-8";

        //通过getInstance获得实例，目前支持的包括：MD2,MD5,SHA-1(SHA),SHA-256,SHA-384,SHA-512
        String[] algorithmArray = {"MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"};

        for (String algorithm : algorithmArray) {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(message.getBytes(charSet));
            byte[] md2EncryptBytes = messageDigest.digest();

            System.out.println(algorithm + "\n" + ByteUtil.byteToHex(md2EncryptBytes));
            System.out.println(messageDigest.toString());
        }
    }

}
