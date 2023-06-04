package maxxsoft.conversorCSV.aplication.service.impl;

import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import maxxsoft.conversorCSV.aplication.repository.ContaRepository;
import org.springframework.stereotype.Service;


@Service
public class ContaServiceImpl {

    private final ContaRepository repository;

    public ContaServiceImpl(ContaRepository repository) {
        this.repository = repository;
    }

    public ClassRelacionadas.Conta create(ClassRelacionadas.Conta entity) {
        return repository.save(entity);
    }

}


