package com.photostudio.client.customers;

import com.photostudio.domain.customers.Funeral;
import com.photostudio.services.customers.IFuneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

/**
 * Created by AP on 8/21/2016.
 */
@RestController
@RequestMapping(value="/funeral")
public class FuneralAPI {
    @Autowired
    private IFuneralService service;
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    @ResponseBody
    public Funeral findById(@PathVariable Long id){
        return service.readById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Funeral create(@RequestBody Funeral resource){
        return service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Funeral resource){
        service.update(resource);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Funeral> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        Funeral funeralDelete=service.readById(id);
        if(funeralDelete!=null){
            service.delete(funeralDelete);
        }

    }
}
