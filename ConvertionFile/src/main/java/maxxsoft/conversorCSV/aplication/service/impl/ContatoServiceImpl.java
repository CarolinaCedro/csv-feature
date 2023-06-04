package maxxsoft.conversorCSV.aplication.service.impl;

import maxxsoft.conversorCSV.aplication.entities.ClassRelacionadas;
import maxxsoft.conversorCSV.aplication.repository.ContatoRepository;
import org.springframework.stereotype.Service;


@Service
public class ContatoServiceImpl {

    private final ContatoRepository repository;

    public ContatoServiceImpl(ContatoRepository repository) {
        this.repository = repository;
    }


    public ClassRelacionadas.Contato create(ClassRelacionadas.Contato entity) {
        return repository.save(entity);
    }

}


