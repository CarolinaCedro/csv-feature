package maxxsoft.conversorCSV.aplication.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ContaSerializer extends JsonSerializer<ClassRelacionadas.Conta> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void serialize(ClassRelacionadas.Conta conta, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(conta.getId());
    }
}
