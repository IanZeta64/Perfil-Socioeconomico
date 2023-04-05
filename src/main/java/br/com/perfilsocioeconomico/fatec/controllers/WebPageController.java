package br.com.perfilsocioeconomico.fatec.controllers;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.services.EstatisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/estatisticas")
public class WebPageController {
    private final EstatisticasService estatisticasService;

    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping(params = "pergunta")
    public ModelAndView graficos(@RequestParam String pergunta){
        Estatisticas estatisticas = estatisticasService.getStatsByQustionTitle(pergunta);
        ModelAndView mv = new ModelAndView("graficos");
        mv.addObject("estatisticas", estatisticas);
        return mv;

    }


}

