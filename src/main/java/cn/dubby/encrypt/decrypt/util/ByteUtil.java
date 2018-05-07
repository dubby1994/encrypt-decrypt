package cn.dubby.encrypt.decrypt.util;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class ByteUtil {

    private static final String EMPTY_STRING = "";

    public static String byteToHex(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return EMPTY_STRING;
        }
        StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            stringBuilder.append(digits[(b & 0xF0) >>> 4]);
            stringBuilder.append(digits[b & 0x0F]);
        }
        return stringBuilder.toString();
    }

    private final static char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static void main(String[] args) {
        byte[] bytes = {(byte) 0, (byte) 1, (byte) 2, (byte) 3};

        System.out.println(bytes.length * 2);

        String result = byteToHex(bytes);

        System.out.println(result.length());
        System.out.println(result);
    }

}
