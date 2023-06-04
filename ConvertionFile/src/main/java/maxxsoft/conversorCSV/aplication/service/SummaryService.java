package maxxsoft.conversorCSV.aplication.service;

import maxxsoft.conversorCSV.aplication.entities.Summary;
import maxxsoft.conversorCSV.aplication.entities.dto.request.SummaryRequest;
import maxxsoft.conversorCSV.aplication.entities.dto.response.SummaryResponse;
import maxxsoft.conversorCSV.aplication.service.conversion.ConversionSummaryParams;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface SummaryService {

    Summary createSummary (Summary summary) throws ChangeSetPersister.NotFoundException;
    List<Summary> findAllSummarys();

    ByteArrayResource converterForCsv(@RequestBody ConversionSummaryParams params) throws IOException, ParseException;
}
