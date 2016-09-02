package com.photostudio.testFactories;


import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Funeral;
import com.photostudio.factories.customers.FuneralFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by AP on 8/22/2016.
 */
public class FuneralFactoryTest {
    @Test
    public void testCreate(){
        Address address= new Address.Builder()
                .postalCode("7100")
                .streetName("24946 katali")
                .suburb("Mfuleni")
                .build();
        Funeral funeral = FuneralFactory.getFuneral("Encore",address);

        Assert.assertEquals(funeral.getName(),"Encore");
    }
}
