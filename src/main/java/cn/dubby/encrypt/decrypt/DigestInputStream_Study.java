package cn.dubby.encrypt.decrypt;

import cn.dubby.encrypt.decrypt.util.ByteUtil;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class DigestInputStream_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        //通过getInstance获得实例，目前支持的包括：MD2,MD5,SHA-1(SHA),SHA-256,SHA-384,SHA-512
        String[] algorithmArray = {"MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"};

        for (String algorithm : algorithmArray) {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            DigestInputStream digestInputStream = new DigestInputStream(new FileInputStream("pom.xml"), messageDigest);
            digestInputStream.on(true);

            readFile(digestInputStream);

            System.out.println(algorithm + "\n" + ByteUtil.byteToHex(messageDigest.digest()));
            System.out.println(messageDigest.toString());
        }
    }

    private static void readFile(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(bytes)) > 0) {
            //pass
        }
    }

}
