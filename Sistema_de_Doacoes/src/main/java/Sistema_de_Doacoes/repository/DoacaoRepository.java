package Sistema_de_Doacoes.repository;

import Sistema_de_Doacoes.model.Doacao;
import Sistema_de_Doacoes.model.Doador;
import Sistema_de_Doacoes.model.Instituicao;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    //Metodo para mostrar doação realizada por doador no periodo entre datas
    List<Doacao> findByDoadorAndDataBetween(Doador doador, LocalDate DataInicial, LocalDate DataFinal);

    //Exibi a soma total que uma instituição recebeu em valor
    @Query("SELECT COALESCE(FUNCTION('ROUND', SUM(d.valor), 2), 0)FROM Doacao d WHERE d.instituicao = :instituicao")
    Double somaTotalPorInstitucao(@Param("instituicao")Instituicao instituicao);

}
