package maxxsoft.conversorCSV.aplication.repository;

import maxxsoft.conversorCSV.aplication.entities.Collaborator;
import maxxsoft.conversorCSV.aplication.entities.Summary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SummaryRepository extends MongoRepository<Summary,String> {
}
