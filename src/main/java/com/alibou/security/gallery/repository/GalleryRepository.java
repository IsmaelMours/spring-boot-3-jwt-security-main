package com.alibou.security.gallery.repository;


import com.alibou.security.gallery.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {


}