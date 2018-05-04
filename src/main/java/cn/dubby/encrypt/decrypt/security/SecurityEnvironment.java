package cn.dubby.encrypt.decrypt.security;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * Created by yangzheng03 on 2018/5/3.
 */
public class SecurityEnvironment {

    public static void main(String[] args) {
        for (Provider p : Security.getProviders()) {
            System.out.println(p);

            for (Map.Entry<Object, Object> entry : p.entrySet()) {
                System.out.println("\t" + entry.getKey());
            }
        }
    }

}
