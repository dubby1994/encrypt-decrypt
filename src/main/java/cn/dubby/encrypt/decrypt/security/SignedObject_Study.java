package cn.dubby.encrypt.decrypt.security;

import java.io.IOException;
import java.security.*;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class SignedObject_Study {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String charSet = "UTF-8";
        //待做签名的原始信息
        byte[] data = "Data Signature".getBytes(charSet);
        //实例化KeyPairGenerator，并指定DSA算法
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        //初始化
        keyPairGenerator.initialize(1024);
        //生成KeyPair对象
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        //实例化Signature
        Signature signature = Signature.getInstance(keyPairGenerator.getAlgorithm());
        //实例化SignedObject
        SignedObject signedObject = new SignedObject(data, keyPair.getPrivate(), signature);
        //获得签名
        byte[] sign = signedObject.getSignature();

        //验证签名
        boolean status = signedObject.verify(keyPair.getPublic(), signature);
        System.out.println(status);
    }

}
