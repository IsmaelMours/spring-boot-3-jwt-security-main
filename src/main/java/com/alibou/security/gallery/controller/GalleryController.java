package com.alibou.security.gallery.controller;

import java.io.IOException;


import com.alibou.security.gallery.model.Gallery;
import com.alibou.security.gallery.repository.GalleryRepository;
import com.alibou.security.gallery.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Controller
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private GalleryRepository galleryRepository;

    @PostMapping("/upload")
    public ResponseEntity<Gallery> uploadImg(@RequestPart("event") String event, @RequestParam("file") MultipartFile file) {
        try {
            Gallery gallery = new Gallery();
            gallery.setEventName(event);
            gallery.setImgName(file.getOriginalFilename());
            gallery.setImg(file.getBytes());
            gallery.setImgType(file.getContentType());
            galleryRepository.save(gallery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/image/{Id}")
    public ResponseEntity<Gallery> getImage(@PathVariable Long Id) {
        Gallery gallery = galleryService.getFile(Id);
        return ResponseEntity.ok(gallery);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<Gallery>> ploadImages(@RequestPart("event")String event, @RequestParam("file") MultipartFile[] files) {
        try {
            List<Gallery> savedImgs = galleryService.saveImages(event,files);
            return ResponseEntity.ok(savedImgs);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Gallery>> getAllImages() {
        List<Gallery> galleries = galleryRepository.findAll();
        return ResponseEntity.ok(galleries);
    }


}
