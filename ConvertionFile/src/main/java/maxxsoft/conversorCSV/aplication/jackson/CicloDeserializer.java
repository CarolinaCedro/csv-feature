package maxxsoft.conversorCSV.aplication.jackson;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CicloDeserializer extends JsonDeserializer<ClassRelacionadas.Ciclo> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public ClassRelacionadas.Ciclo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String contaId = jsonParser.getText();

        // Consultar a conta no banco de dados
        Query query = new Query().addCriteria(Criteria.where("id").is(contaId));
        ClassRelacionadas.Ciclo conta = mongoOperations.findOne(query, ClassRelacionadas.Ciclo.class);

        return conta;
    }
}
