package br.com.perfilsocioeconomico.fatec.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;


public record Estatisticas(String pergunta, Set<Map.Entry<String, Long>> estatisticas) {

}
