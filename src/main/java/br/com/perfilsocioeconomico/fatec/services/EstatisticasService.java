package br.com.perfilsocioeconomico.fatec.services;
import br.com.perfilsocioeconomico.fatec.exceptions.QuestionNotFoundException;
import br.com.perfilsocioeconomico.fatec.exceptions.WordCloudNotFoundException;
import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.model.Pergunta;
import br.com.perfilsocioeconomico.fatec.model.Resposta;
import br.com.perfilsocioeconomico.fatec.repositories.PerguntaRepository;
import br.com.perfilsocioeconomico.fatec.util.InterfaceGrafica;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstatisticasService {
    private final PerguntaRepository perguntaRepository;



    public Estatisticas getStatsById(Long perguntaId) {
        Pergunta pergunta = perguntaRepository.findById(perguntaId).orElseThrow(() -> new WordCloudNotFoundException("Pergunta nao encontrada"));
        return getEstatisticas(pergunta);
    }

    private static Estatisticas getEstatisticas(Pergunta pergunta) {
        Map<String, Long> mapRespostas;
        if (pergunta.getListaDeResposta().get(0).getResposta().contains(";")) {
            Optional<String> respostas = pergunta.getListaDeResposta().stream().map(Resposta::getResposta)
                    .reduce(String::concat);
            System.out.println(respostas.get());
            String[] respostasFormatado = respostas.get().split(";");
            mapRespostas = Arrays.stream(respostasFormatado).collect(Collectors.groupingBy(resp -> resp, Collectors.counting()));
        } else {
            mapRespostas = pergunta.getListaDeResposta().stream()
                    .filter(resposta -> !resposta.getResposta().equals("Nenhuma resposta"))
                    .collect(Collectors.groupingBy(Resposta::getResposta, Collectors.counting()));
        }


        Estatisticas estatisticas = new Estatisticas(pergunta.getPergunta(), mapRespostas.entrySet());
        return estatisticas;
    }

    public Estatisticas getStatsByQustionTitle(String perguntaTitulo) {
        Pergunta pergunta = perguntaRepository.findAll().stream().filter(perg -> perg.getPergunta().equals(perguntaTitulo)).findFirst().orElseThrow(() -> new WordCloudNotFoundException("Pergunta nao encontrada"));
        return getEstatisticas(pergunta);


    }

    @Transactional
    public Map<String, Estatisticas> getAllStats() {
        List<Pergunta> perguntasLista = perguntaRepository.findAll();
       Map<String, Estatisticas> estatisticasMap = new HashMap<>();
        for (int i = 0; i < perguntasLista.size()-1; i++) {
            Map<String, Long> mapContagemRespostas;
            if (perguntasLista.get(i).getListaDeResposta().stream().anyMatch(resposta -> resposta.getResposta().contains(";"))) {
                String respostasConcatenada = perguntasLista.get(i).getListaDeResposta().stream()
                        .map(Resposta::getResposta)
                        .filter(resposta -> !resposta.equals("Nenhuma resposta"))
                        .reduce(String::concat).orElseThrow(() -> new QuestionNotFoundException("Pergunta nao encontrada"));
                String[] respostasFormatadoVetor = respostasConcatenada.split(";");
                mapContagemRespostas = Arrays.stream(respostasFormatadoVetor).collect(Collectors.groupingBy(resp -> resp, Collectors.counting()));
            } else {
                mapContagemRespostas = perguntasLista.get(i).getListaDeResposta().stream()
                        .filter(resposta -> !resposta.getResposta().equals("Nenhuma resposta"))
                        .collect(Collectors.groupingBy(Resposta::getResposta, Collectors.counting()));
            }

            Estatisticas estatisticas = new Estatisticas(perguntasLista.get(i).getPergunta(), mapContagemRespostas.entrySet());
            estatisticasMap.put(estatisticas.getPergunta(), estatisticas);
        }
    return estatisticasMap;
    }
}
