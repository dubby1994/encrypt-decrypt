package cn.dubby.encrypt.decrypt.security;

import cn.dubby.encrypt.decrypt.util.ByteUtil;

import java.io.*;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class DigestOutputStream_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        final String message = "hello world!", charSet = "UTF-8";
        //通过getInstance获得实例，目前支持的包括：MD2,MD5,SHA-1(SHA),SHA-256,SHA-384,SHA-512
        String[] algorithmArray = {"MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"};

        for (String algorithm : algorithmArray) {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            DigestOutputStream digestOutputStream = new DigestOutputStream(new FileOutputStream(System.currentTimeMillis() + ".txt"), messageDigest);
            digestOutputStream.write(message.getBytes(charSet));

            System.out.println(algorithm + "\n" + ByteUtil.byteToHex(messageDigest.digest()));
            System.out.println(messageDigest.toString());
        }
    }

}
