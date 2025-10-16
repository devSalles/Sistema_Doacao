package Sistema_de_Doacoes.repository;

import Sistema_de_Doacoes.model.Doacao;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao,Long> {
}
