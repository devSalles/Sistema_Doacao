package Sistema_de_Doacoes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_doador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false) @PastOrPresent(message = "Data deve ser passada ou de agora")
    private LocalDate dataCadastro = LocalDate.now();

    @OneToMany(mappedBy = "doador", cascade = CascadeType.ALL)
    private List<Doacao> doacoes;
}
