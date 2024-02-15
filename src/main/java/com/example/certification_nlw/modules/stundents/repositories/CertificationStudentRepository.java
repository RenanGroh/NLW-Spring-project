package com.example.certification_nlw.modules.stundents.repositories;

import com.example.certification_nlw.modules.stundents.entities.CertificationStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID> {

    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email and c.technology = :technology")
    List<CertificationStudentEntity> findByStudentEmailAndTechnology(String email, String technology);

}
