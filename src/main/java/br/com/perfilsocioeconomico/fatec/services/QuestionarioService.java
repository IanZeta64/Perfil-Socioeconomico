package br.com.perfilsocioeconomico.fatec.services;

import br.com.perfilsocioeconomico.fatec.model.Pergunta;
import br.com.perfilsocioeconomico.fatec.model.Questionario;
import br.com.perfilsocioeconomico.fatec.model.Resposta;
import br.com.perfilsocioeconomico.fatec.repositories.PerguntaRepository;
import br.com.perfilsocioeconomico.fatec.repositories.QuestionarioRepository;
import br.com.perfilsocioeconomico.fatec.repositories.RespostaRepository;
import br.com.perfilsocioeconomico.fatec.util.ManipuladorDeArquivo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class QuestionarioService {

    private final QuestionarioRepository questionarioRepository;
    private final RespostaRepository respostaRepository;
    private final PerguntaRepository perguntaRepository;
    private final ManipuladorDeArquivo manipuladorDeArquivo;


    public Questionario save(){
       manipuladorDeArquivo.excelToCSV();
        List<List<String>> listaComListaDeRespostasLeitura = manipuladorDeArquivo.lerArquivo();
        System.out.println(listaComListaDeRespostasLeitura.get(0).size());

//
//
//

        int tamanhoListaProcessavel = listaComListaDeRespostasLeitura.get(0).size() -1;
           for (int i = 0; i < tamanhoListaProcessavel; i++) {
               for (List<String> listadeRespostasLeitura : listaComListaDeRespostasLeitura.stream().skip(1).toList()) {
                   Resposta resposta = new Resposta(listadeRespostasLeitura.get(i), listaComListaDeRespostasLeitura.get(0).get(i));

                   respostaRepository.save(resposta);
               }
               Pergunta pergunta  = new Pergunta();
               pergunta.setPergunta(listaComListaDeRespostasLeitura.get(0).get(i));
               var respostasLista = respostaRepository.findAll().stream()
                       .filter(resposta -> resposta.getPergunta().equals(pergunta.getPergunta())).toList();
               pergunta.setListaDeResposta(respostasLista);
              perguntaRepository.save(pergunta);
           }
        Questionario questionario = new Questionario();
           var perguntasLista = perguntaRepository.findAll();
          questionario.setListaDePerguntas(perguntasLista);
          questionario.setDataDeLeitura(LocalDateTime.now());
          return questionarioRepository.save(questionario);
    }
}
