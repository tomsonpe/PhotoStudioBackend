package com.photostudio.testRepositories;

import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Religion;
import com.photostudio.repositories.customers.ReligionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.junit.Assert;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Created by AP on 8/24/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class ReligionRepositoryTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    ReligionRepository repository;

    @Test
    public void testCreate() throws Exception {
        Address address= new Address.Builder()
                .postalCode("4960")
                .streetName("412 derby")
                .suburb("Butterwort")
                .build();
        Religion createEntity = new Religion.Builder()
                .name("Siya")
                .address(address)
                .build();
        Religion insertedEntity = repository.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull("CREATE",insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {

        Iterable<Religion> funeralSet = repository.findAll();
        Assert.assertNotNull(" READ ALL",funeralSet);
    }

    @Test
    public void testUpdate() throws Exception {
        Religion entity = repository.findOne(3L);
        Religion updataEntity = new Religion.Builder()
                .copy(entity)
                .name("Encore")
                .build();
        repository.save(updataEntity);
        Religion newEntity = repository.save(updataEntity);
        Assert.assertEquals(" UPDATE ENTITY", newEntity.getName(),entity.getName());
    }

    @Test
    public void testDelete () throws Exception
    {
        Religion religion = repository.findOne(2L);
        if ( religion != null)
        {
            Assert.assertNotNull("Before deleting"+religion);
            repository.delete(2L);
            Religion deletedEvent = repository.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
