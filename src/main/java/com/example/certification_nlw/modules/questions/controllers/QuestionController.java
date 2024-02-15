package com.example.certification_nlw.modules.questions.controllers;

import com.example.certification_nlw.modules.questions.dto.AlternativeResultDTO;
import com.example.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.example.certification_nlw.modules.questions.entities.AlternativeEntity;
import com.example.certification_nlw.modules.questions.entities.QuestionEntity;
import com.example.certification_nlw.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDTO(question))
                .collect(Collectors.toList());

        return toMap;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity questions) {
        var questionResultDTO = QuestionResultDTO.builder()
                .id(questions.getId())
                .technology(questions.getTechnology())
                .description(questions.getDescription()).build();

        List<AlternativeResultDTO> alternativeResultDTOS =
                questions.getAlternatives()
                .stream().map(alternative -> mapAlternativeDTO(alternative))
                .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativeResultDTOS);
        return questionResultDTO;
    }

    static AlternativeResultDTO mapAlternativeDTO(AlternativeEntity alternativeResultDTO) {
        return AlternativeResultDTO.builder()
                .id(alternativeResultDTO.getId())
                .description(alternativeResultDTO.getDescription()).build();


    }
}
