package cn.dubby.codec;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64_Study {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String message = "http://www.dubby.cn 这是杨正的个人博客！ 😄", charSet = "UTF-8";

        byte[] encodeBase64 = Base64.encodeBase64(message.getBytes(charSet));
        byte[] encodeUrlBase64 = Base64.encodeBase64URLSafe(message.getBytes(charSet));
        System.out.println(new String(encodeBase64, charSet));
        System.out.println(new String(encodeUrlBase64, charSet));

        byte[] decodeBase64 = Base64.decodeBase64(encodeBase64);
        byte[] decodeUrlBase64 = Base64.decodeBase64(encodeUrlBase64);
        System.out.println(new String(decodeBase64, charSet));
        System.out.println(new String(decodeUrlBase64, charSet));
    }

}
