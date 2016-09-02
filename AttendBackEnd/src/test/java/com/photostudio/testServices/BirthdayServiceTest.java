package com.photostudio.testServices;


import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.BirthdayParty;
import com.photostudio.factories.customers.BirthdayPartyFactory;
import com.photostudio.repositories.customers.BirthdayPartyRepository;
import com.photostudio.services.customers.implementations.BirthdayPartyService;
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
public class BirthdayServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    BirthdayPartyService service;
   // Long id;

    @Autowired
    private BirthdayPartyRepository repository;

    private String nameOfPerson;
    Address address;

    BirthdayPartyFactory factory;
    @Test
    public void testCreateParty() throws Exception{
        nameOfPerson="Encore";
        address=new Address.Builder()
                .postalCode("7100")
                .streetName("24946 kataliStreet")
                .suburb("Mfuleni")
                .build();
        BirthdayParty birthdayParty=BirthdayPartyFactory.getCustomer(nameOfPerson,address);
        BirthdayParty savedBirthdayParty=service.create(birthdayParty);

        Assert.assertNotNull(savedBirthdayParty);

    }
    @Test//(dependsOnMethods = "testCreateParty")
    public void testReadAll() throws Exception {
        Iterable<BirthdayParty> partyIterable=service.readAll();
        assertNotNull(partyIterable);
    }
    @Test//(dependsOnMethods = "testReadAll")
    public void testUpdateParty() throws Exception {
        BirthdayParty party= service.readById(1L);
        BirthdayParty updateParty = new BirthdayParty.Builder()
                .copy(party)
                .name("Tomson")
                .build();
        BirthdayParty updatedParty=service.update(updateParty);
        Assert.assertEquals("UPDATE TEST",updatedParty.getNameOfPerson(),"Tomson");
    }
    @Test//(dependsOnMethods = "testUpdateParty")
    public void testDelete() throws Exception {
        BirthdayParty foundParty = service.readById(2L);
        if(foundParty !=null) {
            assertNotNull("BEFORE DELETE TEST",foundParty);
            service.delete(foundParty);
            BirthdayParty deletedAccount = service.readById(2L);

            assertNull("DELETE TEST",deletedAccount);
        }
    }
}
