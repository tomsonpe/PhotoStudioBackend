package com.photostudio.services.customers.implementations;

import com.photostudio.domain.customers.Religion;
import com.photostudio.repositories.customers.ReligionRepository;
import com.photostudio.services.customers.IReligion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by AP on 8/24/2016.
 */
@Service
public class ReligionService implements IReligion {
    @Autowired
    private ReligionRepository repo;

    @Override
    public Religion create(Religion entity) {
        return repo.save(entity);
    }

    @Override
    public Religion readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<Religion> readAll(){
        Iterable<Religion> graduations=repo.findAll();
        Set gradSet=new HashSet();

        for(Religion birthdayParty: graduations){
            gradSet.add(graduations);
        }

        return gradSet;
    }

    @Override
    public Religion update(Religion entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Religion entity){
        repo.delete(entity);
    }
}
