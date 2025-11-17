package Sistema_de_Doacoes.repository;

import Sistema_de_Doacoes.model.Doacao;
import Sistema_de_Doacoes.model.Doador;
import Sistema_de_Doacoes.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao,Long> {

    //Realizar procura de Doador
    List<Doacao> findByDoador(Doador doador);

    //Realizar procura de instituição
    List<Instituicao> findByInstituicao(Instituicao instituicao);

    //Verifica existência de CPF vinculado para exclusão
    boolean existsByDoadorCpf(String cpf);

    //Verifica existência de CNPJ vinculado para exclusão
    boolean existsByInstituicaoCnpj(String cnpj);
}
