/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class PasswordTest {

    public static void main(String[] args) {
        char[] password1 = {'a', 'b', 'c', '1', '2', '?', '-', ' ', '*'};
        String password2 = new String(password1);

        byte[] byte1 = new byte[password1.length];
        for (int i = 0; i < password1.length; ++i) {
            byte1[i] = (byte) password1[i];
        }

        byte[] byte2 = password2.getBytes();

        for (int i = 0; i < password1.length; ++i) {
            System.out.println((char) byte1[i] + "\t" + byte1[i] + "\t" + byte2[i]);
        }
    }

}
