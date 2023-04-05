package br.com.perfilsocioeconomico.fatec;
import br.com.perfilsocioeconomico.fatec.services.SwingCommandLineRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@RequiredArgsConstructor
public class PerfilSocioeconomicoApplication  {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        ConfigurableApplicationContext context = SpringApplication.run(PerfilSocioeconomicoApplication.class, args);
        SwingCommandLineRunner swingCommandLineRunner = context.getBean(SwingCommandLineRunner.class);

        try {
            swingCommandLineRunner.run(args);
        } catch (Exception e) {
            System.err.println(e);;
        }
    }
}
