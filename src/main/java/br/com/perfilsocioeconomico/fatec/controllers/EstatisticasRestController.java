package br.com.perfilsocioeconomico.fatec.controllers;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.services.EstatisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/estatisticas")
@RequiredArgsConstructor
public class EstatisticasRestController {

    private final EstatisticasService estatisticasService;

    @GetMapping("/{perguntaId}")
    public Estatisticas getStats(@PathVariable("perguntaId") Long perguntaId){
        return estatisticasService.getStats(perguntaId);
    }
}
