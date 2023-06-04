package maxxsoft.conversorCSV.aplication.controller;

import lombok.RequiredArgsConstructor;
import maxxsoft.conversorCSV.aplication.config.ModelMapperConfig;
import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import maxxsoft.conversorCSV.aplication.service.impl.ContaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController {

    private final ContaServiceImpl contaServiceImpl;
    private final ModelMapperConfig modelMapper;

    @PostMapping
    ResponseEntity<ClassRelacionadas.Conta> create (@RequestBody ClassRelacionadas.Conta request){
        return ResponseEntity.ok(contaServiceImpl.create(request));
    }


}
