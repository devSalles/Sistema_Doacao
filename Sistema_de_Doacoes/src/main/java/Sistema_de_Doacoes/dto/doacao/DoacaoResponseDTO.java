package Sistema_de_Doacoes.dto.doacao;

import Sistema_de_Doacoes.Enum.PagamentoEnum;
import Sistema_de_Doacoes.dto.doador.DoadorResponseDTO;
import Sistema_de_Doacoes.dto.instituicao.InstituicaoResponseDTO;
import Sistema_de_Doacoes.model.Doacao;

import java.time.LocalDate;

public record DoacaoResponseDTO(
        Long id,
        String descricao,
        Double valor,
        LocalDate data,
        PagamentoEnum pagamento,
        DoadorResponseDTO doadorResponseDTO,
        InstituicaoResponseDTO instituicaoResponseDTO

) {
    public static DoacaoResponseDTO fromDoacao(Doacao doacao)
    {
        return new DoacaoResponseDTO(doacao.getId(), doacao.getDescricao(), doacao.getValor(), doacao.getData(), doacao.getMetodoPagamento(),
                doacao.getDoador() != null ? DoadorResponseDTO.fromDoador(doacao.getDoador()): null,
                doacao.getInstituicao() != null ? InstituicaoResponseDTO.fromInstituicao(doacao.getInstituicao()): null);
    }
}
