package Sistema_de_Doacoes.dto.instituicao;

import Sistema_de_Doacoes.model.Instituicao;

import java.time.LocalDate;

public record InstituicaoResponseDTO(
        String nome,
        String cnpj,
        String email,
        String telefone,
        String endereco,
        String cep,
        LocalDate dataCadastro
) {
    public static InstituicaoResponseDTO fromInstituicao(Instituicao instituicao)
    {
        return new InstituicaoResponseDTO(instituicao.getNome(), instituicao.getCnpj(), instituicao.getEmail(), instituicao.getTelefone(),
                instituicao.getEndereco(), instituicao.getCep(), instituicao.getDataCadastro());
    }
}
