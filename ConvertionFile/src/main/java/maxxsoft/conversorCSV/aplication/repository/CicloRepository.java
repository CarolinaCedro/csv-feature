package maxxsoft.conversorCSV.aplication.repository;

import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CicloRepository extends MongoRepository<ClassRelacionadas.Ciclo,String> {
}
