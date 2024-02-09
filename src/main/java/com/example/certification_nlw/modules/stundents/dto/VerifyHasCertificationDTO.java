package com.example.certification_nlw.modules.stundents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyHasCertificationDTO {

    private String Email;
    private String technology;
}
