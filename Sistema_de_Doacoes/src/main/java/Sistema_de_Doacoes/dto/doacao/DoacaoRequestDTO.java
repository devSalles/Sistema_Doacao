package Sistema_de_Doacoes.dto.doacao;

import Sistema_de_Doacoes.Enum.PagamentoEnum;
import Sistema_de_Doacoes.model.Doacao;
import Sistema_de_Doacoes.model.Doador;
import Sistema_de_Doacoes.model.Instituicao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoRequestDTO {

    @NotNull(message = "descrição obrigatória") @NotBlank(message = "descrição obrigatória")
    private String descricao;

    @NotNull(message = "Valor obrigatório") @DecimalMin(value = "0.01", message = "Valor deve ser mais que 0 ")
    private Double valor;

    @NotNull(message = "Pagamento obrigatório") @Enumerated(EnumType.STRING)
    private PagamentoEnum metodoPagamento;

    @NotBlank(message = "CPF obrigatório") @CPF(message = "Formato de CPF inválido")
    String cpf;

    @NotBlank(message = "CNPJ obrigatório") @CNPJ(message = "Formato de CNPJ inválido")
    String cnpj;

    public Doacao toDoacao(Doador doador, Instituicao instituicao)
    {
        Doacao doacaoNew= new Doacao();

        doacaoNew.setDescricao(this.descricao);
        doacaoNew.setValor(this.valor);
        doacaoNew.setMetodoPagamento(this.metodoPagamento);

        if(doacaoNew.getDoador() == null)
        {
            doacaoNew.setDoador(doador);
        }

        if(doacaoNew.getInstituicao() == null)
        {
            doacaoNew.setInstituicao(instituicao);
        }

        return doacaoNew;
    }

    public Doacao updateDoacao(Doacao doacao,Doador doador, Instituicao instituicao)
    {
        doacao.setDescricao(this.getDescricao());
        doacao.setValor(this.getValor());
        doacao.setMetodoPagamento(this.getMetodoPagamento());
        doacao.setDoador(doador);
        doacao.setInstituicao(instituicao);
        return doacao;
    }

}
