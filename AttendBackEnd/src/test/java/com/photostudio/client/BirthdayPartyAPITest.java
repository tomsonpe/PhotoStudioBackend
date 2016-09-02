package com.photostudio.client;



import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.BirthdayParty;
import com.photostudio.factories.customers.BirthdayPartyFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by admin on 2016/08/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)

@WebAppConfiguration
public class BirthdayPartyAPITest extends AbstractTestNGSpringContextTests {
    @Test
    public void testCreate() {
        String URI = "http://localhost:8080/birthdayparty";
        RestTemplate restTemplate = new RestTemplate();
        Address address= new Address.Builder()
                .postalCode("7100")
                .streetName("24946 katali")
                .suburb("Mfuleni")
                .build();
        BirthdayParty party = BirthdayPartyFactory.getCustomer("Encore", address);
        restTemplate.postForObject(URI, party, BirthdayParty.class);
    }

    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/birthdayparty/{id}";
        RestTemplate restTemplate = new RestTemplate();
        BirthdayParty party= restTemplate.getForObject(URI, BirthdayParty.class, "1");
        Assert.assertNotNull(party);
        Assert.assertEquals("Tomson", party.getNameOfPerson());
    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/birthdayparty/{id}";
        RestTemplate restTemplate = new RestTemplate();
        BirthdayParty party= restTemplate.getForObject(URI, BirthdayParty.class, "5");
        if(party!=null) {
            String UPDATE_URI = "http://localhost:8080/birthdayparty";
            BirthdayParty updateParty = new BirthdayParty.Builder()
                    .copy(party)
                    .name("Tomson")
                    .build();
            restTemplate.put(UPDATE_URI,updateParty);
            BirthdayParty UpdatedParty= restTemplate.getForObject(URI, BirthdayParty.class, "1");

            Assert.assertEquals(UpdatedParty.getNameOfPerson(),"Tomson");
        }
    }

    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/birthdayparty";
        RestTemplate restTemplate = new RestTemplate();
        Set parties = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(parties.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/birthdayparty/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"8");
        BirthdayParty party= restTemplate.getForObject(URI, BirthdayParty.class, "8");

        Assert.assertNull(party);


    }
}
