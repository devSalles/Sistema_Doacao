package Sistema_de_Doacoes.repository;

import Sistema_de_Doacoes.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoadorRepository extends JpaRepository<Doador,Long> {

    //Metodo para procura de email
    Doador findByEmail(String email);

    //Metodo para procura de email
    Doador findByCpf(String cpf);
}
