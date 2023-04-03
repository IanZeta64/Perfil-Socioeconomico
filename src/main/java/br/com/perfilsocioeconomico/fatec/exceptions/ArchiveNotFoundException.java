package br.com.perfilsocioeconomico.fatec.exceptions;

public class ArchiveNotFoundException extends RuntimeException{
    public ArchiveNotFoundException(String menssage) {
        super(menssage);
    }
}
