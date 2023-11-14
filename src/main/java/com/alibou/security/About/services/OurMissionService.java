package com.alibou.security.About.services;


import com.alibou.security.About.models.OurMission;
import com.alibou.security.About.repository.OurMissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class OurMissionService {

    @Autowired
    private final OurMissionRepository repository;

    public void createMission(String Content, MultipartFile file) {
        try {
            OurMission ourMission = new OurMission();
            Optional<OurMission> exisitingData = repository.findByColumnName(ourMission.getContent());
            if (exisitingData.isPresent()) {
                throw new RuntimeException("This entry is restricted to one entry, Delete or update the current One");
            } else {
                ourMission.setContent(Content);
                ourMission.setImgName(file.getOriginalFilename());
                ourMission.setImg(file.getBytes());
                ourMission.setImgType(file.getContentType());
                repository.save(ourMission);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    public Stream<OurMission> getAll() {
        return repository.findAll().stream();
    }


    public OurMission getById(Long Id) {
        return repository.findById(Id).orElseThrow();
    }


    public OurMission update(OurMission updateMission, MultipartFile file) {
        updateMission.setContent(updateMission.getContent());
        updateMission.setImg(updateMission.getImg());
        updateMission.setImgName(updateMission.getImgName());
        return repository.save(updateMission);
    }

    public void deleteAbout() {
        repository.deleteAll();
    }
}
