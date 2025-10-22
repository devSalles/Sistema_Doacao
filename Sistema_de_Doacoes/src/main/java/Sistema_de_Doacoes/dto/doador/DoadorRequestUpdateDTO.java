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
public class DoadorRequestUpdateDTO {

    @NotNull(message = "nome obrigatório") @NotBlank(message = "nome obrigatório")
    private String nome;

    @NotNull(message = "telefone obrigatório") @NotBlank(message = "telefone obrigatório")
    private String telefone;

    public Doador updateDoador(Doador doador)
    {
        doador.setNome(this.getNome());
        doador.setTelefone(this.getTelefone());

        return doador;
    }
}
