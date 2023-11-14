package com.alibou.security.About.controller;


import com.alibou.security.About.models.OurMission;
import com.alibou.security.About.repository.OurMissionRepository;
import com.alibou.security.About.services.OurVisionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/vision")
public class OurVisionController {
    private final OurVisionService ourVisionService;

    @Autowired
    private final OurMissionRepository repository;

    @PostMapping("/create")
    public ResponseEntity<OurMission> createMission(@RequestPart("content") String Content, @RequestParam("file") MultipartFile file) throws IOException {
        ourVisionService.createMission(Content, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<OurMission>> getAllData() {
        List<OurMission> ourMissions = repository.findAll();
        return ResponseEntity.ok(ourMissions);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<OurMission> updateMission(@PathVariable Long Id, @RequestPart("content") String Content, @RequestParam("file") MultipartFile file) throws IOException {
        ourVisionService.update(Content, file, Id);
        return ResponseEntity.ok().build();
    }
}
