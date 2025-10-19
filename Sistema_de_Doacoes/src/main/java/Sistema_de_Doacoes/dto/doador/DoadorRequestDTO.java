package Sistema_de_Doacoes.dto.doador;

import Sistema_de_Doacoes.model.Doacao;
import Sistema_de_Doacoes.model.Doador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoadorRequestDTO {

    @NotNull(message = "nome obrigatório") @NotBlank(message = "nome obrigatório")
    private String nome;

    @NotNull(message = "email obrigatório") @NotBlank(message = "email obrigatório")
    private String email;

    @NotNull(message = "CPF obrigatório") @NotBlank(message = "CPF obrigatório")
    private String cpf;

    @NotNull(message = "telefone obrigatório") @NotBlank(message = "telefone obrigatório")
    private String telefone;

    @NotNull(message = "descrição obrigatória") @PastOrPresent(message = "Data não pode ser futura")
    private LocalDate dataCadastro = LocalDate.now();

    public Doador toDoador()
    {
        Doador doador = new Doador();

        doador.setNome(this.nome);
        doador.setEmail(this.email);
        doador.setCpf(this.cpf);
        doador.setTelefone(this.telefone);
        doador.setDataCadastro(this.dataCadastro);

        return doador;
    }

    public Doador updateDoador(Doador doador)
    {
        doador.setNome(this.getNome());
        doador.setEmail(this.getEmail());
        doador.setCpf(this.getCpf());
        doador.setTelefone(this.getTelefone());

        return doador;
    }
}
