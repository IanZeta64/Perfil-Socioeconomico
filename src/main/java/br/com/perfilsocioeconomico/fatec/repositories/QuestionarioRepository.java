package br.com.perfilsocioeconomico.fatec.repositories;

import br.com.perfilsocioeconomico.fatec.model.Questionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {
}
