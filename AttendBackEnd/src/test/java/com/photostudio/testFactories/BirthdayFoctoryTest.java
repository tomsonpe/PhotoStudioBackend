package com.photostudio.testFactories;


import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.BirthdayParty;
import com.photostudio.factories.customers.BirthdayPartyFactory;
import org.testng.Assert;
import org.testng.annotations.Test;



/**
 * Created by AP on 8/22/2016.
 */
public class BirthdayFoctoryTest {
    @Test
    public void testCreate(){
        Address address= new Address.Builder()
                .postalCode("7100")
                .streetName("24946 katali")
                .suburb("Mfuleni")
                .build();
        BirthdayParty birthdayParty = BirthdayPartyFactory.getCustomer("Encore",address);

        Assert.assertEquals(birthdayParty.getNameOfPerson(),"Encore");
    }
}
