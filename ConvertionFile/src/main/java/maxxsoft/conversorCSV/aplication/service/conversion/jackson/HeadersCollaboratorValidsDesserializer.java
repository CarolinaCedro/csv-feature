package maxxsoft.conversorCSV.aplication.service.conversion.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import maxxsoft.conversorCSV.aplication.entities.enums.Collum;
import maxxsoft.conversorCSV.aplication.entities.enums.HeadersCollaboratorValids;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class HeadersCollaboratorValidsDesserializer extends JsonDeserializer<List<Collum>> {
    @Override
    public List<Collum> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ArrayNode node = jsonParser.getCodec().readTree(jsonParser);
        List<Collum> results = new LinkedList<>();
        node.forEach(it -> results.add(HeadersCollaboratorValids.from(it.asText())));
        return results;
    }


}
