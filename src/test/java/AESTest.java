import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class AESTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecretKey secretKey = keyGenerator.generateKey();

    }

}