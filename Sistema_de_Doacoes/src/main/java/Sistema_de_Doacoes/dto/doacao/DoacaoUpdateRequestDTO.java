package Sistema_de_Doacoes.dto.doacao;

import Sistema_de_Doacoes.Enum.PagamentoEnum;
import Sistema_de_Doacoes.model.Doacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoacaoUpdateRequestDTO(

        @NotNull(message = "descrição obrigatória") @NotBlank(message = "descrição obrigatória")
        String descricao,

        @NotNull(message = "Valor obrigatório") @DecimalMin(value = "0.01", message = "Valor deve ser mais que 0 ")
        Double valor,

        @NotNull(message = "Pagamento obrigatório")
        @Enumerated(EnumType.STRING)
        PagamentoEnum metodoPagamento
) {

    public Doacao updateDoacao(Doacao doacao)
    {
        doacao.setDescricao(this.descricao);
        doacao.setValor(this.valor);
        doacao.setMetodoPagamento(this.metodoPagamento);
        return doacao;
    }
}
