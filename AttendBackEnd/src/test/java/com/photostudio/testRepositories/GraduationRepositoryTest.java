package com.photostudio.testRepositories;


import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Graduation;
import com.photostudio.repositories.customers.GraduationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Assert;
import org.testng.annotations.Test;


/**
 * Created by AP on 8/22/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class GraduationRepositoryTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    GraduationRepository repository;

    @Test
    public void testCreate() throws Exception {
        Address address= new Address.Builder()
                .postalCode("4980")
                .streetName("151 Centane")
                .suburb("Kentane")
                .build();
        Graduation createEntity = new Graduation.Builder()
                .name("Tomson")
                .address(address)
                .build();
        Graduation insertedEntity = repository.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull("CREATE",insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {

        Iterable<Graduation> funeralSet = repository.findAll();
        Assert.assertNotNull(" READ ALL",funeralSet);
    }

    @Test
    public void testUpdate() throws Exception {
        Graduation entity = repository.findOne(3L);
        Graduation updataEntity = new Graduation.Builder()
                .copy(entity)
                .name("Encore")
                .build();
        repository.save(updataEntity);
        Graduation newEntity = repository.save(updataEntity);
        Assert.assertEquals(" UPDATE ENTITY", newEntity.getName(),entity.getName());
    }

    @Test
    public void testDelete () throws Exception
    {
        Graduation graduation = repository.findOne(2L);
        if ( graduation != null)
        {
            Assert.assertNotNull("Before deleting"+graduation);
            repository.delete(2L);
            Graduation deletedEvent = repository.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
