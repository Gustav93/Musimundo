package com.musimundo.feeds.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MultipartList implements Serializable{

    private List<MultipartFile> files;

    public MultipartList(){
        files = new ArrayList<>();
    }
    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
