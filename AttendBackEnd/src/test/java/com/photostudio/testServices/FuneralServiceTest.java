package com.photostudio.testServices;

import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Funeral;
import com.photostudio.factories.customers.FuneralFactory;
import com.photostudio.repositories.customers.FuneralRepository;
import com.photostudio.services.customers.implementations.FuneralService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by AP on 8/22/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class FuneralServiceTest extends AbstractTestNGSpringContextTests{
    @Autowired
    FuneralService service;
    Long id;

    @Autowired
    private FuneralRepository repository;

    Funeral funeral;

    private String nameOfPerson;
    Address address;

    FuneralFactory factory;
    @Test
    public void create() throws Exception{
        nameOfPerson="Encore";
        address=new Address.Builder()
                .postalCode("7100")
                .streetName("24946 kataliStreet")
                .suburb("Mfuleni")
                .build();
        funeral= FuneralFactory.getFuneral(nameOfPerson,address);
        repository.save(funeral);
        id=funeral.getId();
        Assert.assertNotNull(id);
    }
    @Test
    public void testCreateFuneral() throws Exception{
        nameOfPerson="Encore";
        address=new Address.Builder()
                .postalCode("7100")
                .streetName("24946 kataliStreet")
                .suburb("Mfuleni")
                .build();
        funeral= FuneralFactory.getFuneral(nameOfPerson,address);
        Funeral savedFuneral=service.create(funeral);

        Assert.assertNotNull(savedFuneral);
    }
    @Test//(dependsOnMethods = "testCreateFuneral")
    public void testReadAll() throws Exception {
        Iterable<Funeral> funeralIterable=service.readAll();
        assertNotNull(funeralIterable);

    }
    @Test//(dependsOnMethods = "testReadAll")
    public void testUpdateFuneral() throws Exception {
        Funeral funeral= service.readById(1L);
        Funeral updateFuneral = new Funeral.Builder()
                .copy(funeral)
                .name("serviceTest")
                .build();
        Funeral updatedFuneral=service.update(updateFuneral);
        Assert.assertEquals("UPDATE TEST",updatedFuneral.getName(),updateFuneral.getName());
    }
    @Test//(dependsOnMethods = "testUpdateFuneral")
    public void testDelete() throws Exception {
        Funeral foundFuneral = service.readById(2L);
        if(foundFuneral !=null) {
            assertNotNull("BEFORE DELETE TEST",foundFuneral);
            service.delete(foundFuneral);
            Funeral deletedFuneral = service.readById(2L);

            assertNull("DELETE TEST",deletedFuneral);
        }
    }
}
