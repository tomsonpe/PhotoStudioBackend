package com.photostudio.client.customers;

import com.photostudio.domain.customers.Religion;
import com.photostudio.services.customers.IReligion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by AP on 8/24/2016.
 */
@RestController
@RequestMapping(value="/religion")
public class ReligionAPI {
    @Autowired
    private IReligion service;
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    @ResponseBody
    public Religion findById(@PathVariable Long id){
        return service.readById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Religion create(@RequestBody Religion resource){
        return service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Religion resource){
        service.update(resource);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Religion> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        Religion religionDelete=service.readById(id);
        if(religionDelete!=null){
            service.delete(religionDelete);
        }

    }
}
