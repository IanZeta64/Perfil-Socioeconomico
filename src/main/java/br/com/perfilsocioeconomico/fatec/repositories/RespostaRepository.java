package br.com.perfilsocioeconomico.fatec.repositories;

import br.com.perfilsocioeconomico.fatec.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

}
