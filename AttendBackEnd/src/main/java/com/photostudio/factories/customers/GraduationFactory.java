package com.photostudio.factories.customers;

import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Graduation;

/**
 * Created by AP on 8/21/2016.
 */
public class GraduationFactory {
    public static Graduation getGraduation(String name, Address address){
        return new Graduation.Builder()
                .name(name)
                .address(address)
                .build();
    }
}
