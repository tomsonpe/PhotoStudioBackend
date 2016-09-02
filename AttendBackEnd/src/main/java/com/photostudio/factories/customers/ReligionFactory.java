package com.photostudio.factories.customers;

import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Religion;

/**
 * Created by AP on 8/24/2016.
 */
public class ReligionFactory {
    public static Religion getFuneral(String name, Address address){
        return new Religion.Builder()
                .name(name)
                .address(address)
                .build();
    }
}
