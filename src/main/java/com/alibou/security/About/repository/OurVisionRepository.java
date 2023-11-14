package com.alibou.security.About.repository;


import com.alibou.security.About.models.OurVision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OurVisionRepository extends JpaRepository<OurVision, Long> {
    @Query("Select des FROM OurVision des where des.content = :content")
    Optional<OurVision> findByColumnName(@Param("content") String columnName);
}
