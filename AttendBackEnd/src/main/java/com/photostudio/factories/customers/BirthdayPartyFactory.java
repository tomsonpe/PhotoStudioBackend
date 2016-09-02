package com.photostudio.factories.customers;


import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.BirthdayParty;

/**
 * Created by admin on 2016/08/20.
 */
public class BirthdayPartyFactory {
    public static BirthdayParty getCustomer(String name,Address address){
        return new BirthdayParty.Builder()
                .name(name)
                .address(address)
                .build();
    }
}
