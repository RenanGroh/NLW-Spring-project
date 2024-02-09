package com.example.certification_nlw.modules.stundents.controllers;

import com.example.certification_nlw.modules.stundents.dto.VerifyHasCertificationDTO;
import com.example.certification_nlw.modules.stundents.useCases.VerifyIfHasCertificationUserCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    // need to use USECASE
    @Autowired
    private VerifyIfHasCertificationUserCase verifyIfHasCertificationUserCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {

        // Email
        // Technology

        var result = this.verifyIfHasCertificationUserCase.execute(verifyHasCertificationDTO);
        System.out.println(verifyHasCertificationDTO);

        if (result) {
            return "User already taken the exam";
        }

        return "User is allowed to take exam";
    }
}
