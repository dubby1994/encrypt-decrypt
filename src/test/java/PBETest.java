import cn.dubby.encrypt.decrypt.util.ByteUtil;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PBETest {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        String algorithm = "PBEWithMD5AndDES", message = "http://www.dubby.cn", password = "dubby", charSet = "UTF-8";

        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

        //根据password生成key
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
        SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

        //生成盐
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(8);
        System.out.println(ByteUtil.byteToHex(salt));
        //迭代次数为16，这个可以自定义
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 16);

        //加密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
        byte[] encodeBytes = cipher.doFinal(message.getBytes(charSet));
        System.out.println(ByteUtil.byteToHex(encodeBytes));

        //解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
        byte[] decodeBytes = cipher.doFinal(encodeBytes);
        System.out.println(new String(decodeBytes));
    }

}
