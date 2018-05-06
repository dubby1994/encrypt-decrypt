import cn.dubby.encrypt.decrypt.util.ByteUtil;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class DHTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidAlgorithmParameterException, InvalidKeyException {
        String algorithm = "DH";

        //a构建publicKey,privateKey
        KeyPairGenerator aKeyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        aKeyPairGenerator.initialize(512);
        KeyPair aKeyPair = aKeyPairGenerator.generateKeyPair();

        PublicKey aPublicKey = aKeyPair.getPublic();
        PrivateKey aPrivateKey = aKeyPair.getPrivate();

        //模拟传参
        byte[] aPublicKeyBytes = aPublicKey.getEncoded();

        //aPublicKey -> b，b根据aPublicKey构建publicKey,privateKey
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(aPublicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PublicKey _aPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        DHParameterSpec dhParameterSpec = ((DHPublicKey) _aPublicKey).getParams();
        KeyPairGenerator bKeyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        bKeyPairGenerator.initialize(dhParameterSpec);
        KeyPair bKeyPair = bKeyPairGenerator.generateKeyPair();
        PublicKey bPublicKey = bKeyPair.getPublic();
        PrivateKey bPrivateKey = bKeyPair.getPrivate();

        //a构建SecretKey
        KeyFactory keyFactory1 = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec x509EncodedKeySpec1 = new X509EncodedKeySpec(bPublicKey.getEncoded());
        PublicKey publicKey1 = keyFactory1.generatePublic(x509EncodedKeySpec1);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec1 = new PKCS8EncodedKeySpec(aPrivateKey.getEncoded());
        PrivateKey privateKey1 = keyFactory1.generatePrivate(pkcs8EncodedKeySpec1);

        KeyAgreement keyAgreement1 = KeyAgreement.getInstance(algorithm);
        keyAgreement1.init(privateKey1);
        keyAgreement1.doPhase(publicKey1, true);
        SecretKey secretKey1 = keyAgreement1.generateSecret("AES");

        //b构建SecretKey
        KeyFactory keyFactory2 = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(aPublicKey.getEncoded());
        PublicKey publicKey2 = keyFactory2.generatePublic(x509EncodedKeySpec2);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec2 = new PKCS8EncodedKeySpec(bPrivateKey.getEncoded());
        PrivateKey privateKey2 = keyFactory2.generatePrivate(pkcs8EncodedKeySpec2);

        KeyAgreement keyAgreement2 = KeyAgreement.getInstance(algorithm);
        keyAgreement2.init(privateKey2);
        keyAgreement2.doPhase(publicKey2, true);
        SecretKey secretKey2 = keyAgreement2.generateSecret("AES");

        System.out.println(ByteUtil.byteToHex(secretKey1.getEncoded()));
        System.out.println(ByteUtil.byteToHex(secretKey2.getEncoded()));
        System.out.println(secretKey1.equals(secretKey2));
    }

}
