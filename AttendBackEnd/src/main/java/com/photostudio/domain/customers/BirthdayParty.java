package com.photostudio.domain.customers;
import com.photostudio.domain.address.Address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by admin on 2016/08/20.
 */
@Entity
public class BirthdayParty{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String nameOfPerson;
    Date dateOfParty;
    Address address;
    public BirthdayParty(){

    }
    public BirthdayParty(Builder builder) {
        this.id=builder.id;
        this.nameOfPerson=builder.nameOfPerson;
        this.dateOfParty=builder.dateOfParty;
        this.address=builder.address;
    }

    public Long getId(){
        return id;
    }
    public String getNameOfPerson(){
        return nameOfPerson;
    }

    public Date getDateOfParty(){
        return dateOfParty;
    }

    public Address getAddress(){
        return address;
    }
    public String getPostalCode(){
        return address.getPostalCode();
    }

    public String getStreetName(){
        return address.getStreetName();
    }

    public String getSuburb(){
        return address.getSuburb();
    }

    public static class Builder{
        Long id;
        String nameOfPerson;
        Address address;
        Date dateOfParty;

        public Builder id(Long id){
            this.id=id;
            return this;
        }
        public Builder name(String nameOfPerson){
            this.nameOfPerson=nameOfPerson;
            return this;
        }
        public Builder address(Address address){
            this.address=address;
            return this;
        }

        public Builder copy(BirthdayParty party){
            this.id=party.id;
            this.nameOfPerson=party.nameOfPerson;
            this.address=party.address;
            return this;
        }
        public BirthdayParty build(){
            return new BirthdayParty(this);
        }
    }
    public void displayTypeOfCustomer(){
        System.out.println("Type of Customer: BirthdayParty");
    }

    @Override
    public int hashCode() {
        return (int)(id ^ (id >>> 32));
    }
}
