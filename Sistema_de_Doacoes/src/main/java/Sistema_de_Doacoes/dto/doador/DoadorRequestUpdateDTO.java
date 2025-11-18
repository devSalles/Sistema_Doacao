package Sistema_de_Doacoes.dto.doador;

import Sistema_de_Doacoes.model.Doador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\d{10,11}$", message = "Telefone inválido. Use DDD e número ex: 1134567890 ou 11987654321")
    private String telefone;

    public Doador updateDoador(Doador doador)
    {
        doador.setNome(this.getNome());
        doador.setTelefone(this.getTelefone());

        return doador;
    }
}
