package com.photostudio.client;

import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Funeral;
import com.photostudio.factories.customers.FuneralFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.junit.Assert;


import java.util.Set;

/**
 * Created by AP on 8/21/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)

@WebAppConfiguration
public class FuneralAPITest extends AbstractTestNGSpringContextTests {
    @Test
    public void testCreate() {
        String URI = "http://localhost:8080/funeral";
        RestTemplate restTemplate = new RestTemplate();
        Address address= new Address.Builder()
                .postalCode("7100")
                .streetName("24946 katali")
                .suburb("Mfuleni")
                .build();
        Funeral funerals = FuneralFactory.getFuneral("Encore", address);
        restTemplate.postForObject(URI, funerals, Funeral.class);
    }

    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/funeral/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Funeral funerals = restTemplate.getForObject(URI, Funeral.class, "3");
        Assert.assertNotNull(funerals);
        Assert.assertEquals(null, funerals.getName());
    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/funeral/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Funeral funerals = restTemplate.getForObject(URI, Funeral.class, "3");
        if(funerals!=null) {
            String UPDATE_URI = "http://localhost:8080/funeral";
            Funeral updateFunerals = new Funeral.Builder()
                    .copy(funerals)
                    .name(null)
                    .build();
            restTemplate.put(UPDATE_URI,updateFunerals);
            Funeral UpdatedFunerals= restTemplate.getForObject(URI, Funeral.class, "3");

            org.junit.Assert.assertEquals(UpdatedFunerals.getName(),null);
        }
    }

    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/funeral";
        RestTemplate restTemplate = new RestTemplate();
        Set funerals = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(funerals.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/funeral/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"28");
        Funeral funerals= restTemplate.getForObject(URI, Funeral.class, "3");

        Assert.assertNotNull(funerals);


    }
}
