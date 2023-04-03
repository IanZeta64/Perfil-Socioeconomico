package br.com.perfilsocioeconomico.fatec.controllers;

import br.com.perfilsocioeconomico.fatec.model.Questionario;
import br.com.perfilsocioeconomico.fatec.services.QuestionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("questionario")
@RequiredArgsConstructor
public class QuestionarioRestController {
    private final QuestionarioService questionarioService;

    @GetMapping
    public Questionario save(){
        return questionarioService.save();
    }
}
