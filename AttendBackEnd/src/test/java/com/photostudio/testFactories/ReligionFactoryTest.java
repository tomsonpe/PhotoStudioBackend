package com.photostudio.testFactories;

import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Religion;
import com.photostudio.factories.customers.ReligionFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by AP on 8/24/2016.
 */
public class ReligionFactoryTest {
    @Test
    public void testCreate(){
        Address address= new Address.Builder()
                .postalCode("7100")
                .streetName("24946 katali")
                .suburb("Mfuleni")
                .build();
        Religion religion = ReligionFactory.getFuneral("Encore",address);

        Assert.assertEquals(religion.getName(),"Encore");
    }
}
