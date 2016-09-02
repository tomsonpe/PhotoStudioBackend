package com.photostudio.services.customers.implementations;

import com.photostudio.domain.customers.Funeral;
import com.photostudio.repositories.customers.FuneralRepository;
import com.photostudio.services.customers.IFuneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by AP on 8/21/2016.
 */
@Service
public class FuneralService implements IFuneralService {
    @Autowired
    private FuneralRepository repo;

    @Override
    public Funeral create(Funeral entity) {
        return repo.save(entity);
    }

    @Override
    public Funeral readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<Funeral> readAll(){
        Iterable<Funeral> funerals=repo.findAll();
        Set funeralSet=new HashSet();

        for(Funeral birthdayParty: funerals){
            funeralSet.add(funerals);
        }

        return funeralSet;
    }

    @Override
    public Funeral update(Funeral entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Funeral entity){
        repo.delete(entity);
    }
}
