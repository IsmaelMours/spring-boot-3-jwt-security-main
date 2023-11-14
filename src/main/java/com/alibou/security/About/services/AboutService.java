package com.alibou.security.About.services;


import com.alibou.security.About.models.About;
import com.alibou.security.About.repository.AboutRepository;
import com.alibou.security.About.services.impl.AboutServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class AboutService implements AboutServiceImpl {

    @Autowired
    private final AboutRepository aboutRepository;

    @Override
    public void createAbout(About about) {
        aboutRepository.save(about);
    }

    @Override
    public Stream<About> getAll() {
        return aboutRepository.findAll().stream();
    }

    @Override
    public About getById(Long Id) {
        return aboutRepository.findById(Id).orElseThrow();
    }

    @Override
    public About update(About updateAbout, Long Id) {
        About about = new About();
        updateAbout.setAboutDescription(updateAbout.getAboutDescription());
        return aboutRepository.save(updateAbout);
    }

    @Override
    public void deleteAbout() {
        aboutRepository.deleteAll();
    }
}
