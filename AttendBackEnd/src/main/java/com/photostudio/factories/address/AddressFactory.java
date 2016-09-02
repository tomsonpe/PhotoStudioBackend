package com.photostudio.factories.address;


import com.photostudio.domain.address.Address;

/**
 * Created by admin on 2016/08/20.
 */
public class AddressFactory {
    public static Address getAddress(String postalCode, String streetName, String suburb){
        return new Address.Builder()
                .postalCode(postalCode)
                .streetName(streetName)
                .suburb(suburb)
                .build();

    }
}
