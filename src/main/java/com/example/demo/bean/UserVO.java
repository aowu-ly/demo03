package com.example.demo.bean;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class UserVO implements Serializable {

    private String user;
    private MultipartFile file;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
