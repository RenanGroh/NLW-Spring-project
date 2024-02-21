package com.example.certification_nlw.modules.stundents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDTO {

    private UUID QuestionID;
    private UUID alternativeID;
    private boolean isCorrect;

}
