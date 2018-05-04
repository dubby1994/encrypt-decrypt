package cn.dubby.encrypt.decrypt.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.CodeSigner;
import java.security.Timestamp;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Date;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class CodeSigner_Study {

    public static void main(String[] args) throws CertificateException, FileNotFoundException {
        //构建CertificateFactory对象，并制定证书类型为X.509
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        //生成CertPath
        CertPath cp = cf.generateCertPath(new FileInputStream("x.cer"));
        //实例化数字时间戳
        Timestamp timestamp = new Timestamp(new Date(), cp);
        //实例化CodeSigner
        CodeSigner codeSigner = new CodeSigner(cp, timestamp);

        System.out.println(codeSigner);
    }

}
