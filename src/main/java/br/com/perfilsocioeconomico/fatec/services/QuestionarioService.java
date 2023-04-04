package br.com.perfilsocioeconomico.fatec.services;

import br.com.perfilsocioeconomico.fatec.exceptions.WordCloudNotFoundException;
import br.com.perfilsocioeconomico.fatec.model.*;
import br.com.perfilsocioeconomico.fatec.repositories.*;
import br.com.perfilsocioeconomico.fatec.util.ManipuladorDeArquivo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionarioService {

    private final QuestionarioRepository questionarioRepository;
    private final RespostaRepository respostaRepository;
    private final PerguntaRepository perguntaRepository;

    private final ContagemDePalavrasRepository contagemDePalavrasRepository;
    private final ManipuladorDeArquivo manipuladorDeArquivo;


    public Questionario save(){

       manipuladorDeArquivo.excelToCSV(); //le arquivo excel, manutencao de erros de estrutura de dados e converte em csv
        List<List<String>> listaComListaDeRespostasLeitura = manipuladorDeArquivo.lerArquivo();//le csv

            //loop para criacao de respostas e perguntas
            List<String> listaRespostaFinal = new ArrayList<>();
           for (int i = 0; i < listaComListaDeRespostasLeitura.get(0).size(); i++) {
               for (List<String> listadeRespostasLeitura : listaComListaDeRespostasLeitura.stream().skip(1).toList()) {
                   if (i < listaComListaDeRespostasLeitura.get(0).size() - 1) {
                       Resposta resposta = new Resposta(listadeRespostasLeitura.get(i), listaComListaDeRespostasLeitura.get(0).get(i));
                       respostaRepository.save(resposta); //salva cada respsota individualmente
                   } else {
                       //manipula strings para contagem de palavras
                       List<String> ultimaRsposta = new ArrayList<>(List.of(listadeRespostasLeitura.get(i).split(" ")));
                       ultimaRsposta = ultimaRsposta.stream().map(string -> string.replace(",", ""))
                              .map(string -> string.replace(".", ""))
                              .map(string -> string.replace("(", ""))
                              .map(string -> string.replace(")", ""))
                              .toList();
                       listaRespostaFinal.addAll(ultimaRsposta);
                   }
               }

               Pergunta pergunta  = new Pergunta();
               pergunta.setPergunta(listaComListaDeRespostasLeitura.get(0).get(i));
               var respostasLista = respostaRepository.findAll().stream()
                       .filter(resposta -> resposta.getPergunta().equals(pergunta.getPergunta())).toList();//recupera respostas para salvar perguntas
               pergunta.setListaDeResposta(respostasLista);
              perguntaRepository.save(pergunta);//salva perguntas
           }



        //criacao de contagem de palavras
        List<ContagemDePalavras> contagemDePalavrasList = listaRespostaFinal.stream()
                .filter(s -> s.length()> 4 && s.contains("r"))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream().map(entry ->{
                    ContagemDePalavras contagemDePalavras = new ContagemDePalavras();
                    contagemDePalavras.setPalavra(entry.getKey());
                    contagemDePalavras.setContagem(entry.getValue());
                    return  contagemDePalavras;}).toList();
        contagemDePalavrasRepository.saveAll(contagemDePalavrasList);//salva todas as contagens
        contagemDePalavrasList = contagemDePalavrasRepository.findAll();//recupera contagem por id



        Questionario questionario = new Questionario();
           var perguntasLista = perguntaRepository.findAll();

          questionario.setListaDePerguntas(perguntasLista);
          questionario.setNuvemDePalavras(contagemDePalavrasList);
          questionario.setDataDeLeitura(LocalDate.now());
          return questionarioRepository.save(questionario);
    }
}
