package maxxsoft.conversorCSV.aplication.repository;

import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends MongoRepository<ClassRelacionadas.Contato,String> {
}
