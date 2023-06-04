package maxxsoft.conversorCSV.aplication.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.build();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ClassRelacionadas.Conta.class, new ContaSerializer());
        module.addDeserializer(ClassRelacionadas.Conta.class, new ContaDeserializer());
        module.addSerializer(ClassRelacionadas.Contato.class, new ContatoSerializer());
        module.addDeserializer(ClassRelacionadas.Contato.class, new ContatoDeserializer());
        module.addSerializer(ClassRelacionadas.Ciclo.class, new CicloSerializer());
        module.addDeserializer(ClassRelacionadas.Ciclo.class, new CicloDeserializer());
        module.addSerializer(ClassRelacionadas.Propriedade.class, new PropriedadeSerializer());
        module.addDeserializer(ClassRelacionadas.Propriedade.class, new PropriedadeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

}
