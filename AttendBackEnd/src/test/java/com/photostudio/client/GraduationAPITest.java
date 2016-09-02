package com.photostudio.client;

import com.photostudio.App;
import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Graduation;
import com.photostudio.factories.customers.GraduationFactory;
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
 * Created by AP on 8/21/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)

@WebAppConfiguration

public class GraduationAPITest extends AbstractTestNGSpringContextTests{
    @Test
    public void testCreate() {
        String URI = "http://localhost:8080/graduation";
        RestTemplate restTemplate = new RestTemplate();
        Address address = null;
        Graduation graduation = GraduationFactory.getGraduation("serviceTest", address);
        restTemplate.postForObject(URI, graduation, Graduation.class);
    }

    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/graduation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Graduation graduation = restTemplate.getForObject(URI, Graduation.class, "1");
        Assert.assertNotNull(graduation);
        Assert.assertEquals("serviceTest", graduation.getName());
    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/graduation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Graduation graduation = restTemplate.getForObject(URI, Graduation.class, "1");
        if(graduation!=null) {
            String UPDATE_URI = "http://localhost:8080/graduation";
            Graduation updateGraduation = new Graduation.Builder()
                    .copy(graduation)
                    .name("serviceTest")
                    .build();
            restTemplate.put(UPDATE_URI,updateGraduation);
            Graduation UpdatedGraduation= restTemplate.getForObject(URI, Graduation.class, "1");

            Assert.assertEquals(UpdatedGraduation .getName(),"serviceTest");
        }
    }

    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/graduation";
        RestTemplate restTemplate = new RestTemplate();
        Set graduations = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(graduations.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/graduation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"1");
        Graduation graduation= restTemplate.getForObject(URI, Graduation.class, "1");
        Assert.assertNull(graduation);
    }
}
