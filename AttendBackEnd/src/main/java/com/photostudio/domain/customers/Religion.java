package com.photostudio.domain.customers;

import com.photostudio.domain.address.Address;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Created by AP on 8/24/2016.
 */
@Entity
public class Religion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String event_name;
    private Address address=new Address.Builder().streetName("katali").suburb("Mfuleni").postalCode("7100").build();

    public Long getId(){return id;}

    public String getName(){
        return event_name;
    }
    public Address getAddress(){return address;}
    public String getPostalCode(){
        return address.getPostalCode();
    }

    public String getStreetName(){
        return address.getStreetName();
    }

    public String getSuburb(){
        return address.getSuburb();
    }
    public Religion(){

    }
    public Religion(Builder builder){
        this.id=builder.id;
        this.event_name=builder.event_name;
    }

    public static class Builder{
        private Long id;
        private String event_name;
        private Address address;

        public Builder id(Long id){
            this.id=id;
            return this;
        }


        public Builder name(String event_name){
            this.event_name=event_name;
            return this;
        }
        public Builder address(Address address){
            this.address=address;
            return this;
        }

        public Builder copy(Religion rel){
            this.id=rel.getId();
            this.event_name=rel.getName();
            this.address=rel.address;
            return this;
        }

        public Religion build(){
            return new Religion(this);
        }
    }
    public void displayTypeOfCustomer(){
        System.out.println("Type of Customer: Religion");
    }
    @Override
    public int hashCode() {
        return (int)(id ^ (id >>> 32));
    }
}
