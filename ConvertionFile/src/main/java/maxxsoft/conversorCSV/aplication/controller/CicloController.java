package maxxsoft.conversorCSV.aplication.controller;

import lombok.RequiredArgsConstructor;
import maxxsoft.conversorCSV.aplication.config.ModelMapperConfig;
import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import maxxsoft.conversorCSV.aplication.entities.Collaborator;
import maxxsoft.conversorCSV.aplication.entities.dto.request.CollaboratorRequest;
import maxxsoft.conversorCSV.aplication.entities.dto.response.CollaboratorResponse;
import maxxsoft.conversorCSV.aplication.service.conversion.ConversionCollaboratorParams;
import maxxsoft.conversorCSV.aplication.service.impl.CicloServiceImpl;
import maxxsoft.conversorCSV.aplication.service.impl.CollaboratorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ciclo")
@RequiredArgsConstructor
public class CicloController {

    private final CicloServiceImpl cicloServiceImpl;
    private final ModelMapperConfig modelMapper;

    @PostMapping
    ResponseEntity<ClassRelacionadas.Ciclo> create (@RequestBody ClassRelacionadas.Ciclo request){
        return ResponseEntity.ok(cicloServiceImpl.create(request));
    }


}
