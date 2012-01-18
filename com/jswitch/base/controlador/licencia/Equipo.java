package com.jswitch.base.controlador.licencia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.jswitch.base.controlador.logger.LoggerUtil;

public class Equipo {

    public static String getSerial() {
        String serial = null;
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                Process p = Runtime.getRuntime().exec("cmd /c dir xserialx");
                BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
                b.readLine();
                String s = b.readLine();
                serial = s.substring(s.indexOf(": ") + 2);
            } else {
                serial = "Linux";
                //TODO LINUX HD SERIAL
            }
        } catch (Exception ex) {
            LoggerUtil.error(Equipo.class, "getSerial", ex);
        }
        return serial;
    }

    public static byte[] encodeText(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //return new String(md.digest(password.getBytes()));
            return (md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            LoggerUtil.error(Equipo.class, "encodeText", ex);
        }
        return null;
    }

    public static String encodeText2(String text) {
        byte bs[] = text.getBytes();
        for (int i = 0; i < bs.length; i++) {
            bs[i] += i + 1;
        }
        return new String(bs);
    }

    public static String decodeText(String encry) {
        byte bs[] = encry.getBytes();
        for (int i = 0; i < bs.length; i++) {
            bs[i] -= i + 1;
        }
        return new String(bs);
    }
}
