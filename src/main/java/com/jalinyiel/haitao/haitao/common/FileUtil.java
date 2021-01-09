package com.jalinyiel.haitao.haitao.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: chenzhe
 * @date: 2021/1/9 2:50
 */
public class FileUtil {

    public static String getCurTime() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
        return tempDate.format(new Date());
    }

}
