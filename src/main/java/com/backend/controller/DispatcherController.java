package com.backend.controller;

import com.backend.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/common")
public class DispatcherController {

    @Autowired
    private MinioUtil minioUtil;

    @PostMapping("/fileUpload")
    public void fileUpload(MultipartFile imgFile) {
        final String s = minioUtil.putObject(imgFile);
        System.out.println(s);
    }

    /**
     * 获取图片的路径【带问号后面的内容】
     * @param filename
     * @return
     */
    @ResponseBody
    @GetMapping("/fileDownload/{filename}")
    public void download(@PathVariable("filename") String filename, HttpServletResponse response) {
        minioUtil.getObject(filename,response);
    }
}