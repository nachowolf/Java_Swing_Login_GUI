package models;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = simpleEncrypt(password);
    }

    public User(String username, char[] password) {
        this.username = username;
        this.password = simpleEncrypt(String.valueOf(password));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String simpleEncrypt(String pw) {
        byte[] encryptArray = Base64.getEncoder().encode(pw.getBytes());
        String en = null;
        try {
            en = new String(encryptArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return en;
    }

    private String simpleDecrypt(String pw) {
        byte[] decryptArray = Base64.getDecoder().decode(pw);
        String de = null;
        try {
            de = new String(decryptArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return de;
    }

}

