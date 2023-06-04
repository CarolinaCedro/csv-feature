package maxxsoft.conversorCSV.aplication.service.impl;

import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import maxxsoft.conversorCSV.aplication.repository.CicloRepository;
import maxxsoft.conversorCSV.aplication.repository.PropriedadeRepository;
import org.springframework.stereotype.Service;


@Service
public class PropriedadeServiceImpl {

    private final PropriedadeRepository repository;

    public PropriedadeServiceImpl(PropriedadeRepository repository) {
        this.repository = repository;
    }


    public ClassRelacionadas.Propriedade create(ClassRelacionadas.Propriedade entity) {
        return repository.save(entity);
    }

}


