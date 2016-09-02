package com.photostudio.services.customers.implementations;


import com.photostudio.domain.customers.BirthdayParty;
import com.photostudio.repositories.customers.BirthdayPartyRepository;
import com.photostudio.services.customers.IBirthdayPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2016/08/20.
 */
@Service
public class BirthdayPartyService implements IBirthdayPartyService {
    @Autowired
    private BirthdayPartyRepository repo;

    @Override
    public BirthdayParty create(BirthdayParty entity) {
        return repo.save(entity);
    }

    @Override
    public BirthdayParty readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<BirthdayParty> readAll(){
        Iterable<BirthdayParty> party=repo.findAll();
        Set partySet=new HashSet();

        for(BirthdayParty birthdayParty: party){
            partySet.add(party);
        }

        return partySet;
    }

    @Override
    public BirthdayParty update(BirthdayParty entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(BirthdayParty entity){
        repo.delete(entity);
    }
}
