package com.alibou.security.About.repository;


import com.alibou.security.About.models.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AboutRepository extends JpaRepository<About, Long> {
    @Query("Select des FROM About des where des.aboutDescription = :aboutDescription")
    Optional<About> findByColumnName(@Param("aboutDescription") String columnName);
}
