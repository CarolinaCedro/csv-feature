package maxxsoft.conversorCSV.aplication.service.impl;

import lombok.AllArgsConstructor;
import maxxsoft.conversorCSV.aplication.config.ModelMapperConfig;
import maxxsoft.conversorCSV.aplication.entities.Collaborator;
import maxxsoft.conversorCSV.aplication.entities.Result;
import maxxsoft.conversorCSV.aplication.entities.dto.request.CollaboratorRequest;
import maxxsoft.conversorCSV.aplication.entities.dto.response.CollaboratorResponse;
import maxxsoft.conversorCSV.aplication.entities.enums.Collum;
import maxxsoft.conversorCSV.aplication.repository.CollaboratorRepository;
import maxxsoft.conversorCSV.aplication.service.CollaboratorService;
import maxxsoft.conversorCSV.aplication.service.conversion.ConversionCollaboratorParams;
import maxxsoft.conversorCSV.aplication.util.AggregationUtils;
import maxxsoft.conversorCSV.aplication.util.CsvConverter;
import maxxsoft.conversorCSV.aplication.util.DelimiterValidator;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@AllArgsConstructor
public class CollaboratorServiceImpl implements CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;
    private final ModelMapperConfig modelMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public CollaboratorResponse createCollaborator(CollaboratorRequest collaborator) {
        collaborator.setDate(LocalDate.now());
        Collaborator collaboratorCreate = collaboratorRepository.save(modelMapper.mapper().map(collaborator,Collaborator.class));
        return modelMapper.mapper().map(collaboratorCreate,CollaboratorResponse.class);
    }



    @Override
    public List<CollaboratorResponse> findAllCollaborator() {
        return collaboratorRepository.findAll().stream().map(this::dtoConverter).collect(Collectors.toList());
    }


    public CollaboratorResponse dtoConverter(Collaborator collaborator){
        return modelMapper.mapper().map(collaborator,CollaboratorResponse.class);
    }

    public void converterForCsv(ConversionCollaboratorParams params) {

        String initialDate = params.getInitialDate();
        String finalDate = params.getFinalDate();
        String delimiter = params.getDelimiter();
        List<Collum> headers = params.getHeaders();

        LocalDate initialDateFormat = LocalDate.parse(initialDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate finalDateFormat = LocalDate.parse(finalDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String delimiterValid = DelimiterValidator.validate(delimiter);

        LinkedList<AggregationOperation>listAggregation = new LinkedList<>();

        listAggregation.add(match(where("date").gte(initialDateFormat).lte(finalDateFormat)));
        listAggregation.add(project("conta","name","date")
                .and(context ->
                        new Document("$dateToString",
                                new Document("date", "$date")
                                        .append("format", "%d/%m/%G")
                        )
                ).as("date"));

        List<Result> resultList = AggregationUtils.executeAggregation(mongoTemplate,"collaborator",headers,listAggregation);



        try {
            CsvConverter.convertToCsv(resultList, headers, delimiterValid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
