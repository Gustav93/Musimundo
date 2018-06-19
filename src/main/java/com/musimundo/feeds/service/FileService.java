package com.musimundo.feeds.service;

import com.musimundo.utilities.FeedType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void saveFile(MultipartFile multipartFile);

    List<String> getFileNames();

    void deleteFiles();
}
