package maxxsoft.conversorCSV.aplication.controller;

import lombok.RequiredArgsConstructor;
import maxxsoft.conversorCSV.aplication.config.ModelMapperConfig;
import maxxsoft.conversorCSV.aplication.entities.Collaborator;
import maxxsoft.conversorCSV.aplication.entities.dto.request.CollaboratorRequest;
import maxxsoft.conversorCSV.aplication.entities.dto.response.CollaboratorResponse;
import maxxsoft.conversorCSV.aplication.service.conversion.ConversionCollaboratorParams;
import maxxsoft.conversorCSV.aplication.service.impl.CollaboratorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/collaborator")
@RequiredArgsConstructor
public class CollaboratorController {

    private final CollaboratorServiceImpl collaboratorService;
    private final ModelMapperConfig modelMapper;

    @PostMapping
    ResponseEntity<CollaboratorResponse> create (@RequestBody CollaboratorRequest request){
        return ResponseEntity.ok(collaboratorService.createCollaborator(request));
    }

    @PostMapping("/convertList")
    @ResponseStatus(HttpStatus.OK)
    public String converterForCsv(@RequestBody ConversionCollaboratorParams params) throws IOException {
        collaboratorService.converterForCsv(params);
        return "Arquivo Gerado!!";
    }

    @GetMapping
    ResponseEntity<List<CollaboratorResponse>>findAll(){
        return ResponseEntity.ok(collaboratorService.findAllCollaborator());
    }


    public CollaboratorResponse dtoConverter(Collaborator collaborator){
        return modelMapper.mapper().map(collaborator,CollaboratorResponse.class);
    }


}
