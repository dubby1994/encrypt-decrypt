import cn.dubby.encrypt.decrypt.util.ByteUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 自己测的时候可以尝试增长message，会抛异常javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
 */
public class RSATest {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        String algorithm = "RSA", message = "http://www.dubby.cn", charSet = "UTF-8";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(1024);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        byte[] privateKeyByte = keyPair.getPrivate().getEncoded();
        byte[] publicKeyByte = keyPair.getPublic().getEncoded();

        System.out.println("privateKey:\n" + ByteUtil.byteToHex(privateKeyByte));
        System.out.println("publicKey:\n" + ByteUtil.byteToHex(publicKeyByte));
        System.out.println();

        //构建privateKey
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        KeyFactory keyFactory1 = KeyFactory.getInstance(algorithm);
        PrivateKey privateKey = keyFactory1.generatePrivate(pkcs8EncodedKeySpec);

        //构建publicKey
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
        KeyFactory keyFactory2 = KeyFactory.getInstance(algorithm);
        PublicKey publicKey = keyFactory2.generatePublic(x509EncodedKeySpec);

        //公钥加密&私钥解密
        {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encode = cipher.doFinal(message.getBytes(charSet));
            System.out.println(ByteUtil.byteToHex(encode));

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            System.out.println(new String(cipher.doFinal(encode), charSet));
        }
        //私钥加密&公钥解密
        {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            byte[] encode = cipher.doFinal(message.getBytes(charSet));
            System.out.println(ByteUtil.byteToHex(encode));

            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            System.out.println(new String(cipher.doFinal(encode), charSet));
        }
    }

}
