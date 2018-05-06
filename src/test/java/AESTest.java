import cn.dubby.encrypt.decrypt.util.ByteUtil;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * IV:初始化向量
 * 在密码学的领域里，初始化向量（英语：initialization vector，缩写为IV），
 * 或译初向量，又称初始变量（starting variable，缩写为SV），
 * 是一个固定长度的输入值。一般的使用上会要求它是随机数或拟随机数（pseudorandom）。
 * 使用随机数产生的初始化向量才能达到语义安全（散列函数与消息验证码也有相同要求），
 * 并让攻击者难以对同一把密钥的密文进行破解。
 * 在区块加密中，使用了初始化向量的加密模式被称为区块加密模式。
 */
public class AESTest {

    public static void main(String[] args) {
        try {
            System.out.println("===ecb===");
            ecb();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("===cbc===");
            cbc();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("===pcbc===");
            pcbc();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ECB mode cannot use IV
     */
    private static void ecb() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        String algorithm = "AES/ECB/PKCS5Padding", charSet = "UTF-8", message = "http://www.dubby.cn";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        //不设置iv加密
        Cipher cipher = Cipher.getInstance(algorithm);

        //设置iv加密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptBytes = cipher.doFinal(message.getBytes(charSet));
        System.out.println(ByteUtil.byteToHex(encryptBytes));

        //设置iv解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        System.out.println(new String(cipher.doFinal(encryptBytes)));

        //设置iv加密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        encryptBytes = cipher.doFinal(message.getBytes(charSet));
        System.out.println(ByteUtil.byteToHex(encryptBytes));

        //设置iv解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        System.out.println(new String(cipher.doFinal(encryptBytes)));
    }

    /**
     * 必须有IV，如果不传，那么会生成一个随机16位的byte数组作为iv
     */
    private static void cbc() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        String algorithm = "AES/CBC/PKCS5Padding", charSet = "UTF-8", message = "http://www.dubby.cn";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        //不设置iv加密，会生成一个随机的16位的byte数组作为iv
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptBytes = cipher.doFinal(message.getBytes(charSet));
        System.out.println(ByteUtil.byteToHex(encryptBytes));

        //设置iv解密
        byte[] ivBytes = cipher.getIV();
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
        System.out.println(new String(cipher.doFinal(encryptBytes)));

        //设置iv加密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec("1234567812345678".getBytes(charSet)));
        encryptBytes = cipher.doFinal(message.getBytes(charSet));
        System.out.println(ByteUtil.byteToHex(encryptBytes));

        //设置iv解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec("1234567812345678".getBytes(charSet)));
        System.out.println(new String(cipher.doFinal(encryptBytes)));

        //设置iv加密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec("1234567812345670".getBytes(charSet)));
        encryptBytes = cipher.doFinal(message.getBytes(charSet));
        System.out.println(ByteUtil.byteToHex(encryptBytes));

        //设置iv解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec("1234567812345670".getBytes(charSet)));
        System.out.println(new String(cipher.doFinal(encryptBytes)));

        //故意弄错加密字符串
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec("1234567812345670".getBytes(charSet)));
        encryptBytes[0] = (byte) 0;
        System.out.println(new String(cipher.doFinal(encryptBytes)));
    }

    /**
     * 必须有IV，如果不传，那么会生成一个随机16位的byte数组作为iv
     * 这个相比于cbc，是一种可以使密文中的微小更改在解密时导致明文大部分错误的模式，并在加密的时候也具有同样的特性。
     */
    private static void pcbc() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        String algorithm = "AES/PCBC/PKCS5Padding", charSet = "UTF-8", message = "http://www.dubby.cn";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance(algorithm);

        //设置iv加密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec("1234567812345678".getBytes(charSet)));
        byte[] encryptBytes = cipher.doFinal(message.getBytes(charSet));
        System.out.println(ByteUtil.byteToHex(encryptBytes));

        //设置iv解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec("1234567812345678".getBytes(charSet)));
        System.out.println(new String(cipher.doFinal(encryptBytes)));

        //设置iv加密
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec("1234567812345670".getBytes(charSet)));
        encryptBytes = cipher.doFinal(message.getBytes(charSet));
        System.out.println(ByteUtil.byteToHex(encryptBytes));

        //设置iv解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec("1234567812345670".getBytes(charSet)));
        System.out.println(new String(cipher.doFinal(encryptBytes)));

        //故意弄错加密字符串
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec("1234567812345670".getBytes(charSet)));
        encryptBytes[0] = (byte) 0;
        System.out.println(new String(cipher.doFinal(encryptBytes)));
    }

}