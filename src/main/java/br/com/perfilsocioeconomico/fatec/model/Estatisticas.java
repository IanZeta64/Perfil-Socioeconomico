package br.com.perfilsocioeconomico.fatec.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Estatisticas implements Comparator<Map.Entry<String, Long>> {
    private String pergunta;
    private List<Map.Entry<String, Long>> estatisticas;


    @Override
    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
        return Long.compare(o1.getValue(), o2.getValue());
    }
}
