package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.FileUtil;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author: chenzhe
 * @date: 2021/1/9 2:16
 */
@RestController
public class UploadController {

    //前端demo
//    <form action="http://10.99.142.209:8080/uploadPic" method="post" enctype="multipart/form-data">
//        <input type="file" name="pic" />
//        <input type="submit" />
//    </form>

    @RequestMapping("/uploadPic")
    ResponseResult imgUpdate1(@RequestParam(value = "pic", required = false) MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 存入服务器的文件名
        String newFileName = FileUtil.getCurTime() + suffixName;
        // 文件上传的路径
        String filePath = "D:\\image_6ryhfgr\\test\\";
        // 上传后的文件路径
        String se = filePath + newFileName;

        File dest = new File(se);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newFileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.failedResult(CommonResultCode.FAILED, newFileName);

    }


}
