package br.com.perfilsocioeconomico.fatec.services;

import br.com.perfilsocioeconomico.fatec.model.*;
import br.com.perfilsocioeconomico.fatec.repositories.*;
import br.com.perfilsocioeconomico.fatec.util.ManipuladorDeArquivo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

//       manipuladorDeArquivo.excelToCSV(); //le arquivo excel, manutencao de erros de estrutura de dados e converte em csv

        List<List<String>> listaComListaDeRespostasLeitura = manipuladorDeArquivo.lerArquivo();//le csv

        String ano = listaComListaDeRespostasLeitura.get(1).get(2).substring(24).trim();
            //loop para criacao de respostas e perguntas
            List<String> listaRespostaFinal = new ArrayList<>();
           for (int i = 0; i < listaComListaDeRespostasLeitura.get(0).size(); i++) {
               for (List<String> listadeRespostasLeitura : listaComListaDeRespostasLeitura.stream().skip(1).toList()) {
                   if (i < listaComListaDeRespostasLeitura.get(0).size() - 1) {
                       Resposta resposta = new Resposta();
                       resposta.setResposta(listadeRespostasLeitura.get(i));
                       resposta.setPergunta(listaComListaDeRespostasLeitura.get(0).get(i));
                       resposta.setAno(ano);
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
               var respostasListaDoRepositorio = respostaRepository.findAll().stream()
                       .filter(resposta -> resposta.getPergunta().equals(pergunta.getPergunta()) && resposta.getAno().equals(ano)).toList();//recupera respostas para salvar perguntas
               pergunta.setListaDeResposta(respostasListaDoRepositorio);
               pergunta.setAno(ano);
              perguntaRepository.save(pergunta);//salva perguntas
           }

        //criacao de contagem de palavras
        List<ContagemDePalavras> contagemDePalavrasList = listaRespostaFinal.stream()
                .filter(s -> s.length() > 3 || s.contains("r") || s.equalsIgnoreCase("TI"))
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream().map(entry ->{
                    ContagemDePalavras contagemDePalavras = new ContagemDePalavras();
                    contagemDePalavras.setPalavra(entry.getKey());
                    contagemDePalavras.setContagem(entry.getValue());
                    contagemDePalavras.setAno(ano);
                    return  contagemDePalavras;}).toList();
        contagemDePalavrasRepository.saveAll(contagemDePalavrasList);//salva todas as contagens

       var contagemDePalavrasListaDoRepositorio = contagemDePalavrasRepository.findAll().stream()
                .filter(contagemDePalavras -> contagemDePalavras.getAno().equals(ano)).toList();//recupera contagem por id



        Questionario questionario = new Questionario();
           var perguntasListaDoRepositorio = perguntaRepository.findAll().stream()
                   .filter(contagemDePalavras -> contagemDePalavras.getAno().equals(ano)).toList();

          questionario.setListaDePerguntas(perguntasListaDoRepositorio);
          questionario.setNuvemDePalavras(contagemDePalavrasListaDoRepositorio);
          questionario.setAno(ano);
          return questionarioRepository.save(questionario);
    }

    public String[] findByYear(){
       return questionarioRepository.findAll().stream().map(Questionario::getAno).toList().toArray(new String[0]);
    }

    public String[] findPerguntas(){
        List<String> perguntasNaoValidas = new ArrayList<>(List.of("ID", "Hora de início", "Hora de conclusão", "Email",
                "Nome", "Informe o número do seu RA."));
       return perguntaRepository.findAll().stream().map(Pergunta::getPergunta).filter(
               string -> !perguntasNaoValidas.contains(string)).toList().toArray(new String[0]);
    }

}
