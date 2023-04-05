package br.com.perfilsocioeconomico.fatec;
import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.repositories.PerguntaRepository;
import br.com.perfilsocioeconomico.fatec.services.EstatisticasService;
import br.com.perfilsocioeconomico.fatec.services.MyCommandLineRunner;
import br.com.perfilsocioeconomico.fatec.util.InterfaceGrafica;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


@SpringBootApplication
@RequiredArgsConstructor
public class PerfilSocioeconomicoApplication  {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        ConfigurableApplicationContext context = SpringApplication.run(PerfilSocioeconomicoApplication.class, args);

        MyCommandLineRunner myCommandLineRunner = context.getBean(MyCommandLineRunner.class);
        try {
            myCommandLineRunner.run(args);
        } catch (Exception e) {
            System.err.println(e);;
        }
    }
}
