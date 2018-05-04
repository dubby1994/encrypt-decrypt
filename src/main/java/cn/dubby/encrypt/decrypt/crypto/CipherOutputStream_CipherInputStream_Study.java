package cn.dubby.encrypt.decrypt.crypto;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class CipherOutputStream_CipherInputStream_Study {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        String algorithm = "DES", charSet = "UTF-8", message = "http://www.dubby.cn";

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance(algorithm);

        String filename = System.currentTimeMillis() + ".txt";

        //输出成文件
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(new File(filename)), cipher);
        cos.write(message.getBytes(charSet));
        cos.flush();
        cos.close();

        //读取文件
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        CipherInputStream cis = new CipherInputStream(new FileInputStream(new File(filename)), cipher);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] resultByte = new byte[1024];
        int length = 0;
        while ((length = cis.read(resultByte)) > 0) {
            byteArrayOutputStream.write(resultByte, 0, length);
        }
        System.out.println(byteArrayOutputStream.toString(charSet));

        boolean deleteResult = new File(filename).delete();
        System.out.println("File deleted:" + deleteResult);
//        //输出成文件
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//        CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(new File(filename)), cipher);
//        DataOutputStream dataOutputStream = new DataOutputStream(cos);
//        dataOutputStream.writeUTF(message);
//        dataOutputStream.flush();
//        dataOutputStream.close();
//
//        //读取文件
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        CipherInputStream cis = new CipherInputStream(new FileInputStream(new File(filename)), cipher);
//        DataInputStream dataInputStream = new DataInputStream(cis);
//        String result = dataInputStream.readUTF();
//        System.out.println(result);
    }

}
