package com.example.certification_nlw.modules.stundents.controllers;

import com.example.certification_nlw.modules.stundents.dto.StudentCertificationAnswerDTO;
import com.example.certification_nlw.modules.stundents.dto.VerifyHasCertificationDTO;
import com.example.certification_nlw.modules.stundents.entities.CertificationStudentEntity;
import com.example.certification_nlw.modules.stundents.useCases.StudentCertificationAnswersUseCase;
import com.example.certification_nlw.modules.stundents.useCases.VerifyIfHasCertificationUserCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;


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

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO)  {
        try {
            var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}


