import cn.dubby.encrypt.decrypt.util.ByteUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD4Test {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String algorithm = "MD4", charSet = "UTF-8", message = "http://www.dubby.cn";
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(message.getBytes(charSet));

        System.out.println(ByteUtil.byteToHex(messageDigest.digest()));
    }

}
