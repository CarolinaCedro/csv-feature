package maxxsoft.conversorCSV.aplication.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import maxxsoft.conversorCSV.aplication.config.ModelMapperConfig;
import maxxsoft.conversorCSV.aplication.entities.Result;
import maxxsoft.conversorCSV.aplication.entities.Summary;
import maxxsoft.conversorCSV.aplication.entities.dto.response.SummaryResponse;
import maxxsoft.conversorCSV.aplication.entities.enums.Collum;
import maxxsoft.conversorCSV.aplication.repository.ContaRepository;
import maxxsoft.conversorCSV.aplication.repository.SummaryRepository;
import maxxsoft.conversorCSV.aplication.service.SummaryService;
import maxxsoft.conversorCSV.aplication.service.conversion.ConversionSummaryParams;
import maxxsoft.conversorCSV.aplication.util.AggregationUtils;
import maxxsoft.conversorCSV.aplication.util.CsvConverter;
import maxxsoft.conversorCSV.aplication.util.DelimiterValidator;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.Transient;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@Service
public class SummaryServiceImpl implements SummaryService {

    @Transient
    @JsonIgnore
    private static final String COLLECTION_CONTAS = "contas";

    @Transient
    @JsonIgnore
    private static final String COLLECTION_PROPRIEDADES = "propriedades";
    @Transient
    @JsonIgnore
    public static final String COLLECTION_COLABORADORES = "colaboradores";
    private static final String COLLECTION_CICLOS = "ciclos";
    private static final String COLLECTION_CONTATOS = "contatos";
    private final MongoTemplate mongoTemplate;
    private final SummaryRepository summaryRepository;
    private final ContaRepository contaRepository;

    private final ModelMapperConfig modelMapper;

    @Autowired
    public SummaryServiceImpl(MongoTemplate mongoTemplate, SummaryRepository summaryRepository, ContaRepository contaRepository, ModelMapperConfig modelMapper) {
        this.mongoTemplate = mongoTemplate;
        this.summaryRepository = summaryRepository;
        this.contaRepository = contaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Summary createSummary(Summary summaryDto) throws ChangeSetPersister.NotFoundException {
        summaryDto.setDate(LocalDate.now());
        return summaryRepository.save(summaryDto);
    }


    @Override
    public List<Summary> findAllSummarys() {
        return summaryRepository.findAll();
    }

    @Override
    public ByteArrayResource converterForCsv(ConversionSummaryParams params) throws IOException, ParseException {
        String initialDate = params.getInitialDate();
        String finalDate = params.getFinalDate();
        String delimiter = params.getDelimiter();
        List<Collum> headers = params.getHeaders();


        LocalDate initialDateFormat = LocalDate.parse(initialDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate finalDateFormat = LocalDate.parse(finalDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String delimiterValid = DelimiterValidator.validate(delimiter);


        final LinkedList<AggregationOperation> aggregationOperations = new LinkedList<>(
                asList(
                        match(Criteria.where("date").gte(initialDateFormat).lte(finalDateFormat)),
                        context -> new Document("$lookup",
                                new Document("from", "conta")
                                        .append("localField", "conta.$id")
                                        .append("foreignField", "_id")
                                        .append("as", "conta")
                        ),
                        unwind("$conta", true),
                        context -> new Document("$lookup",
                                new Document("from", "propriedade")
                                        .append("localField", "propriedade.$id")
                                        .append("foreignField", "_id")
                                        .append("as", "propriedade")
                        ),
                        unwind("$propriedade", true),
                        context -> new Document("$lookup",
                                new Document("from", "ciclo")
                                        .append("localField", "ciclo.$id")
                                        .append("foreignField", "_id")
                                        .append("as", "ciclo")
                        ),
                        unwind("$ciclo", true),
                        context -> new Document("$lookup",
                                new Document("from", "contato")
                                        .append("localField", "contato.$id")
                                        .append("foreignField", "_id")
                                        .append("as", "contato")
                        ),
                        unwind("$contato", true),
                        context -> new Document("$lookup",
                                new Document("from", "colaborador")
                                        .append("localField", "colaborador.$id")
                                        .append("foreignField", "_id")
                                        .append("as", "colaborador")
                        ),

                        //cod_conta", "name_collaborator","valor","tp_despesa","tp_combustivel","tp_document","qtde","tp_consolidacao","observacao","registro","date"
                        unwind("$colaborador", true),
                        project("conta", "ciclo", "contato")
                                .andExpression("conta.nome").as("conta")
                                .andExpression("ciclo.nome").as("ciclo")
                                .andExpression("contato.nome").as("contato")
                                .and("cod_conta").as("cod_conta")
                                .and("name_collaborator").as("name_collaborator")
                                .and("valor").as("valor")
                                .and("tp_despesa").as("tp_despesa")
                                .and("tp_combustivel").as("tp_combustivel")
                                .and("tp_document").as("tp_document")
                                .and("qtde").as("qtde")
                                .and("tp_consolidacao").as("tp_consolidacao")
                                .and("observacao").as("observacao")
                                .and("registro").as("registro")
                                .and(context ->
                                        new Document("$dateToString",
                                                new Document("date", "$date")
                                                        .append("format", "%d/%m/%G")
                                        )
                                ).as("date")

                )
        );

        List<Result> resultList = AggregationUtils.executeAggregation(mongoTemplate, "summary", headers, aggregationOperations);

        byte[] csvBytes = CsvConverter.convertToCsv(resultList, headers, delimiterValid).getByteArray();


        ByteArrayResource resource = new ByteArrayResource(csvBytes);

        return resource;


//        try {
//
//            CsvConverter.convertToCsv(resultList, headers, delimiterValid);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    public SummaryResponse dtoConverterToModel(Summary summary) {
        return modelMapper.mapper().map(summary, SummaryResponse.class);
    }
}


