package maxxsoft.conversorCSV.aplication.controller;

import lombok.RequiredArgsConstructor;
import maxxsoft.conversorCSV.aplication.config.ModelMapperConfig;
import maxxsoft.conversorCSV.aplication.entities.Summary;
import maxxsoft.conversorCSV.aplication.service.conversion.ConversionSummaryParams;
import maxxsoft.conversorCSV.aplication.service.impl.SummaryServiceImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/summary")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryServiceImpl summaryService;
    private final ModelMapperConfig modelMappel;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Summary> create (@RequestBody Summary request) throws ChangeSetPersister.NotFoundException {
        Summary newSummary = summaryService.createSummary(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSummary);
    }


    @GetMapping
    public ResponseEntity<List<Summary>>findAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(summaryService.findAllSummarys());
    }

    @PostMapping("/convertList")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ByteArrayResource> convertListToCsv(@RequestBody ConversionSummaryParams params) {
        try {
            ByteArrayResource resource = summaryService.converterForCsv(params);

            String filename = "conversionCsv.csv";
            MediaType mediaType = MediaType.parseMediaType("text/csv");

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(mediaType)
                    .body(resource);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // Trate a exceção adequadamente, se necessário
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



//    @PostMapping("/convertList")
//    @ResponseStatus(HttpStatus.OK)
//    public String converterForCsv(@RequestBody ConversionSummaryParams params) throws IOException, ParseException {
//        summaryService.converterForCsv(params);
//        return "Arquivo Gerado!!";
//    }


}
