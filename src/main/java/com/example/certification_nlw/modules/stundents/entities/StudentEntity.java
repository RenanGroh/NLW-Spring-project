package com.example.certification_nlw.modules.stundents.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data //lombok alread set getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    private UUID id;
    private String email;
    private List<certificationStudentEntity> certificationStudentEntity;


}
