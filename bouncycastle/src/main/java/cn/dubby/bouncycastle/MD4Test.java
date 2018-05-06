package cn.dubby.bouncycastle;

import cn.dubby.encrypt.decrypt.util.ByteUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import java.util.Map;

public class MD4Test {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        String charSet = "UTF-8", message = "http://www.dubby.cn";

        String[] algorithmArray = {"MD4", "SHA-224"};
        for (String algorithm : algorithmArray) {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm, BouncyCastleProvider.PROVIDER_NAME);
            messageDigest.update(message.getBytes(charSet));

            byte[] digestResult = messageDigest.digest();
            System.out.println(ByteUtil.byteToHex(digestResult));
            System.out.println(new String(Hex.encode(digestResult)));
            System.out.println(Base64.getEncoder().encodeToString(digestResult));
            System.out.println(new String(org.bouncycastle.util.encoders.Base64.encode(digestResult)));


            System.out.println();
            System.out.println();
        }

//        System.out.println();
//
//        Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
//        System.out.println(provider);
//        for (Map.Entry<Object, Object> entry : provider.entrySet()) {
//            System.out.println(entry.getKey() + "\t" + entry.getValue());
//        }
    }

}
