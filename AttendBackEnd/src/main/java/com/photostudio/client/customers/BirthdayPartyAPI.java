package com.photostudio.client.customers;

import com.photostudio.domain.customers.BirthdayParty;
import com.photostudio.services.customers.implementations.BirthdayPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


/**
 * Created by admin on 2016/08/20.
 */

@RestController
@RequestMapping(value="/birthdayparty")
public class BirthdayPartyAPI {
    @Autowired
    private BirthdayPartyService service;
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    @ResponseBody
    public BirthdayParty findById(@PathVariable Long id){
        return service.readById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BirthdayParty create(@RequestBody BirthdayParty resource){
        return service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody BirthdayParty resource){
        service.update(resource);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<BirthdayParty> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        BirthdayParty partyDelete=service.readById(id);
        if(partyDelete!=null){
            service.delete(partyDelete);
        }

    }
}
