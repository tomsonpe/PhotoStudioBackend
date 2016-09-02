package com.photostudio.testRepositories;

import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Funeral;
import com.photostudio.repositories.customers.FuneralRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Created by AP on 8/22/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class FuneralRepositoryTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    FuneralRepository repository;

    private static final String TAG = "EventContact Test";
    @Test
    public void testCreate() throws Exception {
        Address address= new Address.Builder()
                .postalCode("2100")
                .streetName("421 sandton street")
                .suburb("Gauteng")
                .build();
        Funeral updataEntity = new Funeral.Builder()
                .name("Jay")
                .address(address)
                .build();
        Funeral insertedEntity = repository.save(updataEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE",insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {

        Iterable<Funeral> funeralSet = repository.findAll();
        Assert.assertNotNull(" READ ALL"+funeralSet);
    }

    @Test
    public void testUpdate() throws Exception {
        Funeral entity = repository.findOne(3L);
        Funeral updataEntity = new Funeral.Builder()
                .copy(entity)
                .name("Encore")
                .build();
        repository.save(updataEntity);
        Funeral newEntity = repository.save(updataEntity);
        Assert.assertEquals(" UPDATE ENTITY", newEntity.getName(),entity.getName());
    }

    @Test
    public void testDelete () throws Exception
    {
        Funeral funeral = repository.findOne(2L);
        if ( funeral != null)
        {
            Assert.assertNotNull("Before deleting"+funeral);
            repository.delete(2L);
            Funeral deletedEvent = repository.findOne(2L);
            Assert.assertNull("Deleted"+deletedEvent);
        }
    }
}
