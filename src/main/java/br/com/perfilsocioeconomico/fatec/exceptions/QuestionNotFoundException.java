package br.com.perfilsocioeconomico.fatec.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String menssage) {
        super(menssage);
    }
}
