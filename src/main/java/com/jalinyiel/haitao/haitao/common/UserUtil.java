package com.jalinyiel.haitao.haitao.common;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class UserUtil {

    public static String getLogInTag(String username) {
        return encryBase64(String.format("%s%s",username,getCurTime()));
    }

    public static String getCurTime() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return tempDate.format(new Date());
    }

    public static String encryBase64(String word) {
        return Base64.getEncoder().encodeToString(word.getBytes());

    }
}
