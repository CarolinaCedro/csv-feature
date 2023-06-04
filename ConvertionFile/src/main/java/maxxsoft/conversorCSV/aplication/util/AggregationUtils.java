package maxxsoft.conversorCSV.aplication.util;

import lombok.AllArgsConstructor;
import maxxsoft.conversorCSV.aplication.entities.Result;
import maxxsoft.conversorCSV.aplication.entities.enums.Collum;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.data.domain.Sort.by;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@AllArgsConstructor
public class AggregationUtils {

    private final MongoTemplate mongoTemplate;


    public static List<Result> executeAggregation(MongoTemplate mongoTemplate, String collection, List<Collum> headersCollumns , LinkedList<AggregationOperation> operations) {

        /*Aqui eu recebo pego as pipes que são especificas de cada entidade e concateno com a pipe que é generica*/

        final List<String> headerMontadoToString = new LinkedList<>();
        headersCollumns.forEach(it -> {
            headerMontadoToString.add("$" + it.getPath());
            //passei delimitador de forma estatica
            headerMontadoToString.add(";");

        });

        headerMontadoToString.forEach(System.out::println);




        operations.add( project().and(context ->
                new Document("$concat",
                        asList(
                                headerMontadoToString.stream().toArray(String[]::new)
                        )
                )
        ).as("result"));

        //Logo abaixo eu pego todas as pipes e peço para o aggregation montar minha agregação

        final AggregationResults<Result> results = mongoTemplate.aggregate(
                newAggregation(
                        //passando as pipes que já estão na lista de agregação
                        operations
                ),
                collection,
                Result.class
        );


        //retorno a lista
        List<Result> resultList = results.getMappedResults();
        return resultList;
    }
}
