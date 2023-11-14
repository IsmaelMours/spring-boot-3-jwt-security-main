package com.alibou.security.About.services;


import com.alibou.security.About.models.OurVision;
import com.alibou.security.About.repository.OurVisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class OurVisionService {
    @Autowired
    private final OurVisionRepository repository;

    public void createMission(String Content, MultipartFile file) {
        try {
            OurVision ourVision = new OurVision();
            Optional<OurVision> exisitingData = repository.findByColumnName(ourVision.getContent());
            if (exisitingData.isPresent()) {
                throw new RuntimeException("This entry is restricted to one entry, Delete or update the current One");
            } else {
                ourVision.setContent(Content);
                ourVision.setImgName(file.getOriginalFilename());
                ourVision.setImg(file.getBytes());
                ourVision.setImgType(file.getContentType());
                repository.save(ourVision);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    public Stream<OurVision> getAll() {
        return repository.findAll().stream();
    }


    public OurVision getById(Long Id) {
        return repository.findById(Id).orElseThrow();
    }


    public OurVision update(String Content, MultipartFile file, Long Id) throws IOException {
        Optional<OurVision> ID= repository.findById(Id);
        if(ID.isPresent()){
            System.out.println(ID);
            OurVision updateVision = new OurVision();
            updateVision.setContent(Content);
            updateVision.setImg(file.getBytes());
            updateVision.setImgName(file.getOriginalFilename());
            updateVision.setImgType(file.getContentType());
            return repository.save(updateVision);
        }else{
            throw new RuntimeException("Content With that ID doesnt exist");
        }
    }

    public void deleteAbout() {
        repository.deleteAll();
    }
}
