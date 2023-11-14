package com.alibou.security.Blog.service.impl;

import com.alibou.security.Blog.repository.FileUploadRepository;
import com.alibou.security.Blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    FileUploadRepository fileUploadRepository;
    @Override
    public String uploadFile(String path, MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();
        String fileName1 = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

        String filePath = path + File.separator + fileName1;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));

        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream inputStream = new FileInputStream((fullPath));
        return inputStream;
    }

}
