package com.example.certification_nlw.modules.stundents.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data //lombok alread set getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class AnwsersCertificationsEntity {

    private UUID id;
    private UUID certificationID;
    private UUID studentID;
    private UUID questionID;
    private UUID answerID;
    private boolean isCorrect;



}
