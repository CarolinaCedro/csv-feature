package maxxsoft.conversorCSV.aplication.service.impl;

import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import maxxsoft.conversorCSV.aplication.repository.CicloRepository;
import org.springframework.stereotype.Service;


@Service
public class CicloServiceImpl {

    private final CicloRepository repository;

    public CicloServiceImpl(CicloRepository repository) {
        this.repository = repository;
    }


    public ClassRelacionadas.Ciclo create(ClassRelacionadas.Ciclo entity) {
        return repository.save(entity);
    }

}


