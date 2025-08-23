package org.dromara.zyplayer.manage.web;

import org.dromara.utils.client.FileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileManager fileManager;


    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 文件访问URL
     */
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return fileManager.uploadFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }
}
