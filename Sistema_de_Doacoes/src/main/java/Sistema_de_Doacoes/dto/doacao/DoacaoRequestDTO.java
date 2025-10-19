package Sistema_de_Doacoes.dto.doacao;

import Sistema_de_Doacoes.Enum.PagamentoEnum;
import Sistema_de_Doacoes.model.Doacao;
import Sistema_de_Doacoes.model.Doador;
import Sistema_de_Doacoes.model.Instituicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoRequestDTO {

    @NotNull(message = "descrição obrigatória") @NotBlank(message = "descrição obrigatória")
    private String descricao;

    @NotNull(message = "Valor obrigatório") @Positive(message = "O valor da doação deve ser maior que zero")
    private Double valor;

    @NotNull(message = "Data obrigatória") @PastOrPresent(message = "Data da doação não pode ser futura")
    private LocalDate data;

    @NotNull(message = "Pagamento obrigatório")
    private PagamentoEnum metodoPagamento;

    public Doacao toDoacao(Doador doador, Instituicao instituicao)
    {
        Doacao doacaoNew= new Doacao();

        doacaoNew.setDescricao(this.descricao);
        doacaoNew.setValor(this.valor);
        doacaoNew.setData(this.data);
        doacaoNew.setMetodoPagamento(this.metodoPagamento);
        doacaoNew.setDoador(doador);
        doacaoNew.setInstituicao(instituicao);

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
