package Sistema_de_Doacoes.dto.doador;

import Sistema_de_Doacoes.model.Doador;

import java.time.LocalDate;

public record DoadorResponseDTO(
        String nome,
        String email,
        String cpf,
        String telefone,
        LocalDate dataCadastro
) {
    public static DoadorResponseDTO fromDoador(Doador doador)
    {
        return new DoadorResponseDTO(doador.getNome(),doador.getEmail(), doador.getCpf(),doador.getTelefone(),doador.getDataCadastro());
    }

}
