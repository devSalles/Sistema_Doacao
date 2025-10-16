package Sistema_de_Doacoes.repository;

import Sistema_de_Doacoes.model.Doacao;
import Sistema_de_Doacoes.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoadorRepository extends JpaRepository<Doador,Long> {
}
