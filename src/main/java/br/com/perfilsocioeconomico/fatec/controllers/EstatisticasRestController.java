package br.com.perfilsocioeconomico.fatec.controllers;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.services.EstatisticasService;
import br.com.perfilsocioeconomico.fatec.util.InterfaceGrafica;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/estatisticas")
@RequiredArgsConstructor
public class EstatisticasRestController {

    private final EstatisticasService estatisticasService;

    @GetMapping("/{perguntaId}")
    public Estatisticas getStats(@PathVariable("perguntaId") Long perguntaId) {
        return estatisticasService.getStatsById(perguntaId);
    }
//    @GetMapping(params ="pergunta")
//    public Estatisticas getStats(@RequestParam("pergunta") String pergunta) {
//        return estatisticasService.getStatsByQustionTitle(pergunta);
//    }

    @GetMapping("/swing")
    public void getStats() {
        var a = estatisticasService.getAllStats();
        new InterfaceGrafica(a);
    }
}


