package br.com.perfilsocioeconomico.fatec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_questionario")
public class Questionario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "pergunta_id")
    private List<Pergunta> listaDePerguntas;

    private LocalDateTime dataDeLeitura;

}
