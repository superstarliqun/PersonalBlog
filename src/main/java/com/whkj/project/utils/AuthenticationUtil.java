package com.whkj.project.utils;

import com.whkj.project.utils.Aes;
import org.apache.commons.codec.digest.DigestUtils;

//@Component
public class AuthenticationUtil {

    //@Value("${token.salt}")
    private static String tokenSalt = "_~SAASTokenToAES";

    //@Value("${password-salt}")
    private static String passwordSalt = "_SAASPassword~";

    private static String passwordAESKye = "_SAASAESPassword";

    private static String tokenHeader = "Authorization";

    public static String getTokenCode(String username){
        return DigestUtils.md5Hex(username+tokenSalt);
    }

    public static String getTokenAESen(String username,String uuid){
        String result = null;
        try {
            result = Aes.aesEncrypt(uuid + "_" +getTokenCode(username) , tokenSalt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getPasswordAESde(String password){
        String result = null;
        try {
            result = Aes.aesDecrypt(password, passwordAESKye);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[] getTokenAESde(String tokenCode){
        String [] result = null;
        try {
            String code = Aes.aesDecrypt(tokenCode, tokenSalt);
            result = code.split("_");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getPassword (String password){
        return DigestUtils.md5Hex(password+passwordSalt);
    }

    public static String getTokenHeader(){
        return tokenHeader;
    }

    public static void main(String[] args) {
//        String s = EncryptUtil.DESencode("123456", passwordAESKye);
//        System.out.println(s);
        String tokenCode = getPasswordAESde("MTtiYKxgT4Jjst2E02bdmg==");
        System.out.println(tokenCode);
    }

}
