package br.com.perfilsocioeconomico.fatec.services;

import br.com.perfilsocioeconomico.fatec.exceptions.QuestionNotFoundException;
import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.model.Pergunta;
import br.com.perfilsocioeconomico.fatec.model.Resposta;
import br.com.perfilsocioeconomico.fatec.repositories.PerguntaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstatisticasService {
    private final PerguntaRepository perguntaRepository;


    public Estatisticas getStats(Long perguntaId) {
        Pergunta pergunta = perguntaRepository.findById(perguntaId).orElseThrow(() -> new QuestionNotFoundException("Pergunta nao encontrada"));
       Map<String, Long> mapRespostas = pergunta.getListaDeResposta().stream().collect(Collectors.groupingBy(Resposta::getResposta, Collectors.counting()));
        return new Estatisticas(pergunta.getPergunta(), mapRespostas.entrySet());

    }
}
