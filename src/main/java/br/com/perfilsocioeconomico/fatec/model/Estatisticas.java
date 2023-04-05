package br.com.perfilsocioeconomico.fatec.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Estatisticas {
    private String pergunta;
    private Set<Map.Entry<String, Long>> estatisticas;


}
