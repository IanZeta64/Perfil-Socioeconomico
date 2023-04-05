package br.com.perfilsocioeconomico.fatec.services;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.services.EstatisticasService;
import br.com.perfilsocioeconomico.fatec.util.InterfaceGrafica;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@NoArgsConstructor
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private EstatisticasService estatisticasService;


    private boolean hasRun = false;

    @Override
    public void run(String... args) throws Exception {
        if (!hasRun) {
            Map<String, Estatisticas> estatisticasMap = estatisticasService.getAllStats();
            new InterfaceGrafica(estatisticasMap);
            hasRun = true;
        }
    }
}