package Sistema_de_Doacoes.dto.doacao;

import Sistema_de_Doacoes.Enum.PagamentoEnum;
import Sistema_de_Doacoes.model.Doacao;

import java.time.LocalDate;

public record DoacaoResponseDTO(
        String descricao,
        Double valor,
        LocalDate data,
        PagamentoEnum pagamento

) {
    public static DoacaoResponseDTO fromDoacao(Doacao doacao)
    {
        return new DoacaoResponseDTO(doacao.getDescricao(), doacao.getValor(), doacao.getData(), doacao.getMetodoPagamento());
    }
}
