package com.example.certification_nlw.modules.stundents.useCases;


import com.example.certification_nlw.modules.questions.entities.QuestionEntity;
import com.example.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.example.certification_nlw.modules.stundents.dto.StudentCertificationAnswerDTO;
import com.example.certification_nlw.modules.stundents.dto.VerifyHasCertificationDTO;
import com.example.certification_nlw.modules.stundents.entities.AnwsersCertificationsEntity;
import com.example.certification_nlw.modules.stundents.entities.CertificationStudentEntity;
import com.example.certification_nlw.modules.stundents.entities.StudentEntity;
import com.example.certification_nlw.modules.stundents.repositories.CertificationStudentRepository;
import com.example.certification_nlw.modules.stundents.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUserCase verifyIfHasCertificationUserCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = this.verifyIfHasCertificationUserCase.execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if(hasCertification) {
            throw new Exception("You already have a certification");
        }

        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnwsersCertificationsEntity> anwsersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionAnswer().stream().forEach(questionAnswer -> {
                    var question = questionsEntity.stream()
                            .filter(q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true);
                        correctAnswers.incrementAndGet();
                    } else {
                        questionAnswer.setCorrect(false);
                    }

                    var answerCertificationEntity = AnwsersCertificationsEntity.builder()
                            .answerID(questionAnswer.getAlternativeID())
                            .questionID(questionAnswer.getQuestionID())
                            .isCorrect(questionAnswer.isCorrect()).build();

                    anwsersCertifications.add(answerCertificationEntity);
                });

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if(student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .stundentiD(studentID)
                .grade(correctAnswers.get())
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        anwsersCertifications.stream().forEach(anwsersCertification -> {
            anwsersCertification.setCertificationID(certificationStudentEntity.getId());
            anwsersCertification.setCertificationStudentEntity(certificationStudentEntity);
            });

        certificationStudentEntity.setAnwsersCertificationsEntity(anwsersCertifications);

        certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;
    }
}
