package com.example.demo.util;

import com.example.demo.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Upload {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Upload.class);

    public String uploadFile(MultipartFile file){

        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "D:\\ly\\ideaIU-2019.2.3.win\\ideaIU-2019.2.3.win\\mycode\\demo\\src\\main\\resources\\static";
        File dest = new File (filePath +"\\"+ fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return fileName;
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";

        /*if(!file.isEmpty()){
            String fileName=file.getOriginalFilename();
            String filePath="D:\\ly\\ideaIU-2019.2.3.win\\ideaIU-2019.2.3.win\\mycode\\demo\\src\\main\\resources\\static";
            File dest=new File(filePath+"\\"+fileName);
            try{
                file.transferTo(dest);
                LOGGER.info("上传成功");
                //return filePath;
            }catch(IOException e){
                LOGGER.error(e.toString(),e);
            }

        }
        return "文件为空";*/
        }
}
