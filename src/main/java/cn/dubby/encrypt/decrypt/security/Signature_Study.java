package cn.dubby.encrypt.decrypt.security;

import cn.dubby.encrypt.decrypt.util.ByteUtil;

import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class Signature_Study {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
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
        //初始化用于签名操作的Signature
        signature.initSign(keyPair.getPrivate());
        //更新
        signature.update(data);
        //获得签名
        byte[] sign = signature.sign();

        System.out.println(ByteUtil.byteToHex(sign));

        //私钥完成签名，公钥用于验证
        signature.initVerify(keyPair.getPublic());
        //更新
        signature.update(data);
        //获得验证结果
        boolean status = signature.verify(sign);

        System.out.println(status);
    }

}
