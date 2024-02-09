package com.example.certification_nlw.modules.stundents.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data //lombok alread set getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class certificationStudentEntity {
    private UUID stundentiD;
    private UUID id;
    private String technology;
    private int grade;
    List<AnwsersCertificationsEntity> anwsersCertificationsEntityList;
}
