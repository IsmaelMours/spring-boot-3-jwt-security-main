package com.alibou.security.About.repository;


import com.alibou.security.About.models.OurMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OurMissionRepository extends JpaRepository<OurMission, Long> {
    @Query("Select des FROM OurMission des where des.content = :content")
    Optional<OurMission> findByColumnName(@Param("content") String columnName);
}
