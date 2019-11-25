package com.xuecheng.logintest.controller;

import com.xuecheng.framework.model.response.ObjectResponse;
import com.xuecheng.logintest.utils.FtpUtil;
import com.xuecheng.logintest.utils.IDUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("images")
public class UploadController {

    @PostMapping("posts")
    public ObjectResponse<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("\\."));
        String host = "106.54.95.152";
        int port = 80;
        FtpUtil.uploadFile(host, port, "ftpuser",
                "123456", "/home/ftpuser", "/images", fileName, file.getInputStream());
        String imageUrl = "http://" + host + port + "/images/" + fileName;
        return new ObjectResponse<>(20000, "上传成功", imageUrl);
    }
}
