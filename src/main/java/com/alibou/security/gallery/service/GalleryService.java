package com.alibou.security.gallery.service;

import java.io.IOException;

import com.alibou.security.gallery.model.Gallery;
import com.alibou.security.gallery.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



import lombok.*;
import java.util.*;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    public Gallery getFile(Long Id) {
        return galleryRepository.findById(Id).orElseThrow();
    }
    public List<Gallery> saveImages(String event, MultipartFile[] images) throws IOException {
        List<Gallery> savedImg = new ArrayList<>();
        for(MultipartFile img : images) {
            String fileName = StringUtils.cleanPath(img.getOriginalFilename());
            Gallery gallery = new Gallery();
            gallery.setImgName(fileName);
            gallery.setImgType(img.getContentType());
            gallery.setImg(img.getBytes());
            gallery.setEventName(event);
            savedImg.add(galleryRepository.save(gallery));
        }
        return savedImg;
    }

    public List<Gallery> getAllImgs() {
        return galleryRepository.findAll();
    }


}
