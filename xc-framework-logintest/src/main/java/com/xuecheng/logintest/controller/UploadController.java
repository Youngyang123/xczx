package com.xuecheng.logintest.controller;

import com.xuecheng.framework.model.response.ObjectResponse;
import com.xuecheng.logintest.utils.FtpUtil;
import com.xuecheng.logintest.utils.IDUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("images")
@CrossOrigin
public class UploadController {

    @PostMapping("posts")
    public ObjectResponse<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String host = "106.54.95.152";
        FtpUtil.uploadFile(host, 21, "ftpuser",
                "123456", "/home/ftpuser", "/", fileName, file.getInputStream());
        String imageUrl = "http://" + host  + "/" + fileName;
        return new ObjectResponse<>(10000, "上传成功", imageUrl);



    }

    public void test() throws IOException {
        FTPClient ftp = new FTPClient();
        //设置 ip 和端口,写在用户名和密码上面
        ftp.connect("192.168.139.131", 21);
        //设置用户名和密码
        ftp.login("ftpuser", "ftpuser");
        //设置文件类型
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        InputStream is = new FileInputStream("E:/test.jpg");
        //第一个参数存储时名称
        ftp.storeFile("test.jpg", is);
    }
}
