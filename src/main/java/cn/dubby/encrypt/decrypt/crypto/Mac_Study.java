package cn.dubby.encrypt.decrypt.crypto;

import cn.dubby.encrypt.decrypt.util.ByteUtil;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Hash-based Message Authentication Code
 * Created by yangzheng03 on 2018/5/4.
 */
public class Mac_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] input = "MAC".getBytes();

        String algorithm = "HmacMD5";

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

        keyGenerator.init(1024);

        SecretKey secretKey = keyGenerator.generateKey();

        byte[] encoded = secretKey.getEncoded();
        System.out.println(encoded.length);
        System.out.println(ByteUtil.byteToHex(encoded));

        Mac mac = Mac.getInstance(algorithm);

        mac.init(secretKey);

        byte[] output = mac.doFinal(input);

        System.out.println(ByteUtil.byteToHex(output));

        //用另一个密钥来做签名
        SecretKey secretKey1 = new SecretKeySpec(encoded, algorithm);

        Mac mac1 = Mac.getInstance(algorithm);

        mac1.init(secretKey1);

        byte[] output1 = mac.doFinal(input);
        System.out.println(ByteUtil.byteToHex(output1));
    }

}
