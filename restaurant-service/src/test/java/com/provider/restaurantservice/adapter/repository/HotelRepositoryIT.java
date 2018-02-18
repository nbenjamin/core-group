package com.provider.restaurantservice.adapter.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.provider.restaurantservice.domain.Address;
import com.provider.restaurantservice.domain.Hotel;
import com.provider.restaurantservice.domain.Item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRepositoryIT {

    @Autowired
    private HotelRepository subject;

    @Test
    public void findById_validHotelId_returnHotel() {
        assertThat(subject, is(notNullValue()));
        Hotel hotel = new Hotel();
        hotel.setName("Hotel-A");

        Address address = new Address();
        address.setCity("GlenAllen");
        address.setStreet("Dominian Bvld");
        address.setStreetNumber("12");
        address.setState("VA");
        address.setZipcode("23060");

        Item item = new Item();
        item.setName("Fish Fry");
        item.setPrice(5.99f);
        item.setVeggie(Boolean.FALSE);
        item.setAvailableTo(22.00f);
        item.setAvailableFrom(8.00f);


        //address.setHotel(hotel);
        //item.setHotel(hotel);

        hotel.getItems().add(item);
        hotel.setAddress(address);

        subject.save(hotel);

        Hotel response = subject.findAll().iterator().next();
        System.out.println(response);
    }
}