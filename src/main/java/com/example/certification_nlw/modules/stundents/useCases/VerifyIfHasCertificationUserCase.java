package com.example.certification_nlw.modules.stundents.useCases;

import com.example.certification_nlw.modules.stundents.dto.VerifyHasCertificationDTO;
import org.springframework.stereotype.Service;

@Service // camada de serviços
public class VerifyIfHasCertificationUserCase {

    public boolean execute(VerifyHasCertificationDTO dto ) {
        return dto.getEmail().equals("renangroh@gmail.com") && dto.getTechnology().equals("JAVA");
    }
}
