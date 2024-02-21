package com.example.certification_nlw.modules.stundents.useCases;

import com.example.certification_nlw.modules.stundents.dto.VerifyHasCertificationDTO;
import com.example.certification_nlw.modules.stundents.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // camada de serviços
public class VerifyIfHasCertificationUserCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(VerifyHasCertificationDTO dto ) {
        var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}
