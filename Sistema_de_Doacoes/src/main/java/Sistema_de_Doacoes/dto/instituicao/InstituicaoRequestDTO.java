package Sistema_de_Doacoes.dto.instituicao;

import Sistema_de_Doacoes.model.Instituicao;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoRequestDTO {

    @NotNull(message = "nome obrigatório") @NotBlank(message = "nome obrigatório")
    private String nome;

    @NotNull(message = "cnpj obrigatório") @NotBlank(message = "cnpj obrigatório") @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @NotNull(message = "email obrigatório") @NotBlank(message = "email obrigatório") @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "telefone obrigatório") @NotBlank(message = "telefone obrigatório")
    private String telefone;

    @NotNull(message = "endereço obrigatório") @NotBlank(message = "endereço obrigatório")
    private String endereco;

    @NotNull(message = "descrição obrigatória") @NotBlank(message = "descrição obrigatória")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
    private String cep;

    public Instituicao toInstituicao()
    {
        Instituicao instituicao = new Instituicao();

        instituicao.setNome(this.nome);
        instituicao.setCnpj(this.cnpj);
        instituicao.setEmail(this.email);
        instituicao.setTelefone(this.telefone);
        instituicao.setEndereco(this.endereco);
        instituicao.setCep(this.cep);

        return instituicao;
    }

    public Instituicao updateInstituicao(Instituicao instituicao)
    {
        instituicao.setNome(this.getNome());
        instituicao.setEmail(this.getEmail());
        instituicao.setTelefone(this.getTelefone());
        instituicao.setEndereco(this.getEndereco());
        instituicao.setCep(this.getCep());

        return instituicao;
    }
}
