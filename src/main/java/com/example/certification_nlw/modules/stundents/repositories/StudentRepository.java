package com.example.certification_nlw.modules.stundents.repositories;

import com.example.certification_nlw.modules.stundents.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {


    public Optional<StudentEntity> findByEmail(String email);

}
