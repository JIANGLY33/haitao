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
@CrossOrigin
@RestController("upload")
public class UploadController {

    //前端demo
//		<form action="http://10.99.142.209:8080/uploadpic/{dir}" method="post" enctype="multipart/form-data">
//		<input type="file" name="pic" />
//		<input type="submit" />
//		</form>

    @RequestMapping("/uploadpic/{dir}")
    ResponseResult itemUpload(@RequestParam(value = "pic", required = false) MultipartFile file, @PathVariable("dir") String dir){
        if(FileUtil.picDir.contains(dir)) return FileUtil.upload(file, dir);
        else return ResponseResult.failedResult(CommonResultCode.FAILED, "rightUrl:" + FileUtil.picDir);
    }

}
