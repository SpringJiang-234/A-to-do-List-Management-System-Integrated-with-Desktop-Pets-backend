package com.backend.controller;

import com.backend.bean.ResultBean;
import com.backend.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/common")
public class DispatcherController {

    @Autowired
    private MinioUtil minioUtil;

    @PostMapping("/fileUpload")
    @ResponseBody
    public ResultBean<String> fileUpload(MultipartFile imgFile) {
        log.info("收到文件上传请求: 文件名={}, 大小={}", imgFile.getOriginalFilename(), imgFile.getSize());
        
        if (imgFile.isEmpty()) {
            log.error("文件为空");
            return ResultBean.error("文件为空", null);
        }
        
        final String url = minioUtil.putObject(imgFile);
        if (url != null) {
            log.info("文件上传成功: {}", url);
            return ResultBean.success(url, url);
        } else {
            log.error("文件上传失败: minioUtil.putObject 返回 null");
            return ResultBean.error("上传失败", null);
        }
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