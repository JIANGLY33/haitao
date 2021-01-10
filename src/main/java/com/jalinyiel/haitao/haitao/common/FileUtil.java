package com.jalinyiel.haitao.haitao.common;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: chenzhe
 * @date: 2021/1/9 2:50
 */
public class FileUtil {

    public static List<String> picDir = Arrays.asList("item","banner","portrait","activity");

    public static String getCurTime() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
        return tempDate.format(new Date());
    }

    public static ResponseResult upload(MultipartFile file, String dir){
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 存入服务器的文件名
        String newFileName = FileUtil.getCurTime() + suffixName;
        // 上传后的文件路径
        String se = "D:\\image_6ryhfgr\\" + dir + "\\" + newFileName;

        File dest = new File(se);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, dir + "/" + newFileName);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.failedResult(CommonResultCode.FAILED, "error upload");
    }

}
