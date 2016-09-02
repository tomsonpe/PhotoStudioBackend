package com.photostudio.services.customers.implementations;

import com.photostudio.domain.customers.Graduation;
import com.photostudio.repositories.customers.GraduationRepository;
import com.photostudio.services.customers.IGraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by AP on 8/21/2016.
 */
@Service
public class GradutionService implements IGraduationService {
    @Autowired
    private GraduationRepository repo;

    @Override
    public Graduation create(Graduation entity) {
        return repo.save(entity);
    }

    @Override
    public Graduation readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<Graduation> readAll(){
        Iterable<Graduation> graduations=repo.findAll();
        Set gradSet=new HashSet();

        for(Graduation birthdayParty: graduations){
            gradSet.add(graduations);
        }

        return gradSet;
    }

    @Override
    public Graduation update(Graduation entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Graduation entity){
        repo.delete(entity);
    }
}
