package maxxsoft.conversorCSV.aplication.repository;

import maxxsoft.conversorCSV.aplication.entities.Collaborator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollaboratorRepository extends MongoRepository<Collaborator,String> {
}
