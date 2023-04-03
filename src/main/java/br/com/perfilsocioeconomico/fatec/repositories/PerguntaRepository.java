package br.com.perfilsocioeconomico.fatec.repositories;

import br.com.perfilsocioeconomico.fatec.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    Pergunta findByPergunta(String pergunta);
}
