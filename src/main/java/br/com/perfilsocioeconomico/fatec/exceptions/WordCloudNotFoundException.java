package br.com.perfilsocioeconomico.fatec.exceptions;

public class WordCloudNotFoundException extends RuntimeException{
    public WordCloudNotFoundException(String menssage) {
        super(menssage);
    }
}