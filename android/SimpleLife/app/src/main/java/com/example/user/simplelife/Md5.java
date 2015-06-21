package com.example.user.simplelife;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Created by Smith on 2015/6/21.
 */
public class Md5
{
    public static String md5(String src)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] barr = md.digest(src.getBytes());
            return bytesToHex(barr);
        } catch (NoSuchAlgorithmException ex)
        {
            System.out.println(ex.toString());
            return src;
        }
    }
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
