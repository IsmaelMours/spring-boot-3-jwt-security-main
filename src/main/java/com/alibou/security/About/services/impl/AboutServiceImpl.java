package com.alibou.security.About.services.impl;



import com.alibou.security.About.models.About;

import java.util.stream.Stream;

public interface AboutServiceImpl {
    public void createAbout(About about);

    public Stream<About> getAll();

    public About getById(Long Id);

    public About update(About updateAbout, Long Id);

    public void deleteAbout();
}
