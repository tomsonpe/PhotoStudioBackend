package com.photostudio.client;


import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Religion;
import com.photostudio.factories.customers.ReligionFactory;
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
 * Created by AP on 8/24/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)

@WebAppConfiguration
public class ReligionAPITest extends AbstractTestNGSpringContextTests {
    @Test
    public void testCreate() {
        String URI = "http://localhost:8080/religion";
        RestTemplate restTemplate = new RestTemplate();
        Address address= new Address.Builder()
                .postalCode("7100")
                .streetName("24946 katali")
                .suburb("Mfuleni")
                .build();
        Religion religion = ReligionFactory.getFuneral("serviceTest", address);
        restTemplate.postForObject(URI, religion, Religion.class);
    }

    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/religion/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Religion religion = restTemplate.getForObject(URI, Religion.class, "3");
        Assert.assertNotNull(religion);

        Assert.assertEquals(null, religion.getName());
    }
    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/religion/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Religion religion = restTemplate.getForObject(URI, Religion.class, "3");
        if(religion!=null) {
            String UPDATE_URI = "http://localhost:8080/religion";
            Religion updateReligions = new Religion.Builder()
                    .copy(religion)
                    .name("serviceTest")
                    .build();
            restTemplate.put(UPDATE_URI,updateReligions);
            Religion UpdatedReligions= restTemplate.getForObject(URI, Religion.class, "3");

            Assert.assertEquals(UpdatedReligions .getName(),"serviceTest");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/religion";
        RestTemplate restTemplate = new RestTemplate();
        Set religions = restTemplate.getForObject(URI,Set.class);

        Assert.assertTrue(religions.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/religion/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"1");
        Religion religion= restTemplate.getForObject(URI, Religion.class, "3");

        Assert.assertNull(religion);
    }
}
