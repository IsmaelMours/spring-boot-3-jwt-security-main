package com.alibou.security.About.controller;



import com.alibou.security.About.models.About;
import com.alibou.security.About.repository.AboutRepository;
import com.alibou.security.About.services.AboutService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@AllArgsConstructor
@RequestMapping("/about")
public class AboutController {
    private final AboutService aboutService;

    @Autowired
    AboutRepository aboutRepository;

    @PostMapping("/save")
    public About create(@RequestBody About about ){
        Optional<About> existingDes = aboutRepository.findByColumnName(about.getAboutDescription());
        if(existingDes.isPresent()){
            throw new RuntimeException("This entry is restricted to one entry, Delete or update the current One");
        }else{
            return aboutRepository.save(about);
        }
    }

    @PutMapping("/{id}")
    public About updateAbout(@RequestBody About about,@PathVariable Long id) {
        log.info("About ID - "+ id);
        Long aboutID = aboutService.getById(id).getId();
        if(aboutID != null){
            about.setAboutDescription(about.getAboutDescription());
            return aboutService.update(about, id);
        }else{
            throw new RuntimeException("About - AboutID"+ id);
        }

    }

    @GetMapping("/getAll")
    public Stream<About> getContactInfo() {
        return aboutService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
         aboutService.getById(id);
        log.info("About ID"+ id);
        return new ResponseEntity<>(aboutService.getById(id), HttpStatus.OK);
    }
    @DeleteMapping("/del")
    public void deleteAboutText() {
        aboutService.deleteAbout();
    }

}
