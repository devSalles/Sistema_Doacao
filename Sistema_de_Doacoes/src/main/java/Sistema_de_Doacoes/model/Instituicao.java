package Sistema_de_Doacoes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_instituicao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instituicao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false,unique = true)
    private String cnpj;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private LocalDate dataCadastro = LocalDate.now();

    @OneToMany(mappedBy = "instituicao",cascade = CascadeType.ALL)
    private List<Doacao> doacoes;
}


