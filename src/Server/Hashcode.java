package Server;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

class Hashcode {
    public Hashcode() {

    }
    public static String crypt(User user)  {
        return getString(user, user.getSalt());
    }
    public static String crypt(User user,String salt)  {
        return getString(user, salt);
    }

    private static String getString(User user, String salt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(salt.getBytes());
        byte[] hashedPassword = md.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
        return byteArrayToString(hashedPassword);
    }

    private static String byteArrayToString(byte[] array){
        String result=null;
        for (byte i:array) {
            result=result+i;
        }
        return  result;
    }

}
