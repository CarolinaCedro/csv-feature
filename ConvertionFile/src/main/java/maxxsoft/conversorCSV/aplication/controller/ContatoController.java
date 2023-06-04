package maxxsoft.conversorCSV.aplication.controller;

import lombok.RequiredArgsConstructor;
import maxxsoft.conversorCSV.aplication.config.ModelMapperConfig;
import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import maxxsoft.conversorCSV.aplication.service.impl.ContatoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoServiceImpl contatoServiceImpl;

    @PostMapping
    ResponseEntity<ClassRelacionadas.Contato> create (@RequestBody ClassRelacionadas.Contato request){
        return ResponseEntity.ok(contatoServiceImpl.create(request));
    }


}
