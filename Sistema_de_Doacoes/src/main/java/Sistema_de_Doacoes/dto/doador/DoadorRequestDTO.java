package Sistema_de_Doacoes.dto.doador;

import Sistema_de_Doacoes.model.Doador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Doador toDoador()
    {
        Doador doador = new Doador();

        doador.setNome(this.nome);
        doador.setEmail(this.email);
        doador.setCpf(this.cpf);
        doador.setTelefone(this.telefone);

        return doador;
    }
}
