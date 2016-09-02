package com.photostudio.testServices;

import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Graduation;
import com.photostudio.factories.customers.GraduationFactory;
import com.photostudio.repositories.customers.GraduationRepository;
import com.photostudio.services.customers.implementations.GradutionService;
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
public class GraduationServiceTest extends AbstractTestNGSpringContextTests{
    @Autowired
    GradutionService service;

    Long id;

    @Autowired
    private GraduationRepository repository;

    Graduation grad;

    private String nameOfPerson;
    Address address;

    GraduationFactory factory;
    @Test
    public void testCreateGraduation() throws Exception{
        nameOfPerson="Encore";
        address=new Address.Builder()
                .postalCode("7100")
                .streetName("24946 kataliStreet")
                .suburb("Mfuleni")
                .build();
        grad= GraduationFactory.getGraduation(nameOfPerson,address);
        Graduation savedGraduation=service.create(grad);

        Assert.assertNotNull(savedGraduation);
    }
    @Test//(dependsOnMethods = "testCreateGraduation")
    public void testReadAll() throws Exception {
        Iterable<Graduation> graduationIterable=service.readAll();
        assertNotNull(graduationIterable);

    }
    @Test//(dependsOnMethods = "testReadAll")
    public void testUpdateGraduation() throws Exception {
        Graduation graduation= service.readById(1L);
        Graduation updateGraduation = new Graduation.Builder()
                .copy(graduation)
                .name("serviceTest")
                .build();
        Graduation updatedGraduation=service.update(updateGraduation);
        Assert.assertEquals("UPDATE TEST",updatedGraduation.getName(),updateGraduation.getName());
    }

    @Test//(dependsOnMethods = "testUpdateGraduation")
    public void testDelete() throws Exception {
        Graduation foundGraduation = service.readById(2L);
        if (foundGraduation != null) {
            assertNotNull("BEFORE DELETE TEST", foundGraduation);
            service.delete(foundGraduation);
            Graduation deletedGraduation = service.readById(2L);

            assertNull("DELETE TEST", deletedGraduation);
        }
    }
}
