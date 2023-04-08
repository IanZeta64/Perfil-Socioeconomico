package br.com.perfilsocioeconomico.fatec.services;
import br.com.perfilsocioeconomico.fatec.view.InterfaceGrafica;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SwingCommandLineRunner implements CommandLineRunner {

    private final QuestionarioService questionarioService;
    private final EstatisticasService estatisticasService;
    private boolean hasRun = false;

    @Override
    public void run(String... args) throws Exception {
        if (!hasRun) {
            new InterfaceGrafica(this.questionarioService, this.estatisticasService);
            hasRun = true;
        }
    }
}