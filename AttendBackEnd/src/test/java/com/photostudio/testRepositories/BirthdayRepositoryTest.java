package com.photostudio.testRepositories;

import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.BirthdayParty;
import com.photostudio.repositories.customers.BirthdayPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by AP on 8/22/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class BirthdayRepositoryTest extends AbstractTestNGSpringContextTests{
    private Long id;

    @Autowired
    private BirthdayPartyRepository repository;
    private static final String TAG = "EventContact Test";
    @Test
    public void testCreate() throws Exception {
        Address address= new Address.Builder()
                .postalCode("7100")
                .streetName("482 7de laan")
                .suburb("Bluedowns")
                .build();
        BirthdayParty createEntity = new BirthdayParty.Builder()
                .name("Simpra")
                .address(address)
                .build();
        BirthdayParty insertedEntity = repository.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE",insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {

        Iterable<BirthdayParty> partySet = repository.findAll();
        Assert.assertNotNull(" READ ALL",partySet);
    }

    @Test
    public void testUpdate() throws Exception {
        BirthdayParty entity = repository.findOne(3L);
        BirthdayParty updataEntity = new BirthdayParty.Builder()
                .copy(entity)
                .name("Encore")
                .build();
        repository.save(updataEntity);
        BirthdayParty newEntity = repository.save(updataEntity);
        Assert.assertEquals(TAG + " UPDATE ENTITY", newEntity.getNameOfPerson(),entity.getNameOfPerson());
    }

    @Test
    public void testDelete () throws Exception
    {
        BirthdayParty party = repository.findOne(2L);
        if ( party != null)
        {
            Assert.assertNotNull("Before deleting"+party);
            repository.delete(2L);
            BirthdayParty deletedEvent = repository.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
