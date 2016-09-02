package com.photostudio.testServices;

import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Religion;
import com.photostudio.factories.customers.ReligionFactory;
import com.photostudio.services.customers.implementations.ReligionService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
/**
 * Created by AP on 8/24/2016.
 */
public class ReligionServiceTest  extends AbstractTestNGSpringContextTests{
    @Autowired
    private ReligionService service;
    @Test
    public void testCreateReligion() throws Exception{
        String nameOfPerson="Encore";
        Address address=new Address.Builder()
                .postalCode("7100")
                .streetName("24946 kataliStreet")
                .suburb("Mfuleni")
                .build();
        Religion religion= ReligionFactory.getFuneral(nameOfPerson,address);
        Religion savedReligion=service.create(religion);

        Assert.assertNotNull(savedReligion);
    }
    @Test//(dependsOnMethods = "testCreateReligion")
    public void testReadAll() throws Exception {
        Iterable<Religion> religionIterable=service.readAll();
        assertNotNull(religionIterable);

    }
    @Test//(dependsOnMethods = "testReadAll")
    public void testUpdateReligion() throws Exception {
         Religion religion= service.readById(1L);
        Religion updateReligion = new Religion.Builder()
                .copy(religion)
                .name("serviceTest")
                .build();
        Religion updatedReligion=service.update(updateReligion);
        Assert.assertEquals("UPDATE TEST",updatedReligion.getName(),updateReligion.getName());
    }

    @Test//(dependsOnMethods = "testUpdateReligion")
    public void testDelete() throws Exception {
        Religion foundReligion = service.readById(2L);
        if (foundReligion != null) {
            assertNotNull("BEFORE DELETE TEST", foundReligion);
            service.delete(foundReligion);
            Religion deletedGraduation = service.readById(2L);

            assertNull("DELETE TEST", deletedGraduation);
        }
    }

}
