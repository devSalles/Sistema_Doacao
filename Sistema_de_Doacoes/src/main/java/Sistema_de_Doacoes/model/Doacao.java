package Sistema_de_Doacoes.model;

import Sistema_de_Doacoes.Enum.PagamentoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_doacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private PagamentoEnum metodoPagamento;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;
}
