package maxxsoft.conversorCSV.aplication.jackson;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.IOException;



@Component
public class ContaDeserializer extends StdDeserializer<ClassRelacionadas.Conta> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public ContaDeserializer() {
        this(null);
    }

    public ContaDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ClassRelacionadas.Conta deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String contaId = jsonParser.getText();

        Query query = Query.query(Criteria.where("id").is(contaId));
        ClassRelacionadas.Conta conta = mongoTemplate.findOne(query, ClassRelacionadas.Conta.class);

        return conta;
    }
}


//@Component
//public class ContaDeserializer extends JsonDeserializer<ClassRelacionadas.Conta> {
//
//    @Autowired
//    private MongoOperations mongoOperations;
//
//    @Override
//    public ClassRelacionadas.Conta deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        String contaId = jsonParser.getText();
//
//        // Consultar a conta no banco de dados
//        Query query = new Query().addCriteria(Criteria.where("id").is(contaId));
//        ClassRelacionadas.Conta conta = mongoOperations.findOne(query, ClassRelacionadas.Conta.class);
//
//        return conta;
//    }
//}
