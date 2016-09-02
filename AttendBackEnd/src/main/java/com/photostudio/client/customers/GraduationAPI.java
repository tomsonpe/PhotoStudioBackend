package com.photostudio.client.customers;

import com.photostudio.domain.customers.Graduation;
import com.photostudio.services.customers.IGraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


/**
 * Created by AP on 8/21/2016.
 */
@RestController
@RequestMapping(value="/graduation")
public class GraduationAPI {
    @Autowired
    private IGraduationService service;
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    @ResponseBody
    public Graduation findById(@PathVariable Long id){
        return service.readById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Graduation create(@RequestBody Graduation resource){
        return service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Graduation resource){
        service.update(resource);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Graduation> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        Graduation graduationDelete=service.readById(id);
        if(graduationDelete!=null){
            service.delete(graduationDelete);
        }

    }
}
