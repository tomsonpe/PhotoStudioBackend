package com.photostudio.factories.customers;

import com.photostudio.domain.address.Address;
import com.photostudio.domain.customers.Funeral;

/**
 * Created by AP on 8/21/2016.
 */
public class FuneralFactory {
    public static Funeral getFuneral(String name, Address address){
        return new Funeral.Builder()
                .name(name)
                .address(address)
                .build();
    }
}
