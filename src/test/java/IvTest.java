import java.io.UnsupportedEncodingException;

public class IvTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String charSet = "UTF-8";
        byte[] bytes = "i".getBytes(charSet);
        System.out.println(bytes.length);
    }

}
