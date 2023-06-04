package maxxsoft.conversorCSV.aplication.controller;

import lombok.RequiredArgsConstructor;
import maxxsoft.conversorCSV.aplication.config.ModelMapperConfig;
import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import maxxsoft.conversorCSV.aplication.service.impl.PropriedadeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propriedade")
@RequiredArgsConstructor
public class PropriedadeController {

    private final PropriedadeServiceImpl propriedadeServiceImpl;

    @PostMapping
    ResponseEntity<ClassRelacionadas.Propriedade> create (@RequestBody ClassRelacionadas.Propriedade request){
        return ResponseEntity.ok(propriedadeServiceImpl.create(request));
    }


}
