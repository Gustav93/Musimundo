package com.musimundo.feeds.service;

import com.musimundo.utilities.FeedType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    private final String PATH = "upload_files\\";

    @Override
    public void saveFile(MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();


        try {
            File file = new File(PATH + fileName);

            InputStream is = multipartFile.getInputStream();
            FileOutputStream fos = new FileOutputStream(file);

            int dato = is.read();

            while(dato != -1)
            {
                fos.write(dato);
                dato = is.read();
            }

            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getFileNames()
    {
        File filePath = new File(PATH);
        File[] files = filePath.listFiles();
        List<String> res = new ArrayList<>();

        for(File file : files)
            res.add(file.getPath());

        return res;
    }

    @Override
    public void deleteFiles() {
        File filePath = new File(PATH);
        File[] files = filePath.listFiles();
        for (File file : files)
            file.delete();
    }
}
