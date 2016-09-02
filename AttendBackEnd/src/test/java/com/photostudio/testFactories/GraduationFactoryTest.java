package com.photostudio.testFactories;

import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Graduation;
import com.photostudio.factories.customers.GraduationFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by AP on 8/22/2016.
 */
public class GraduationFactoryTest {
    @Test
    public void testCreate(){
        Address address= new Address.Builder()
                .postalCode("7100")
                .streetName("24946 katali")
                .suburb("Mfuleni")
                .build();
        Graduation graduation = GraduationFactory.getGraduation("Encore",address);

        Assert.assertEquals(graduation.getName(),"Encore");
    }
}
