package Sistema_de_Doacoes.repository;

import Sistema_de_Doacoes.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao,Long> {

    //Responsável por procurar CNPJ
    Instituicao findByCnpj(String cnpj);

    //Responsável por procurar Email
    Instituicao findByEmail(String email);

    //Responsável por procurar nome
    Instituicao findByNome(String nome);

    Instituicao findByTelefone(String telefone);
}
