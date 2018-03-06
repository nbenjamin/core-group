package com.provider.restaurantservice.adapter.repository;

import static org.hamcrest.CoreMatchers.equalTo;
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

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRepositoryIT {

    @Autowired
    private HotelRepository subject;

    @Test
    public void findById_validHotelId_returnHotel() {
        assertThat(subject, is(notNullValue()));
        Hotel hotel = createHotel();

        subject.save(hotel);

        Hotel response = subject.findAll().iterator().next();
        System.out.println(response);
    }

    @Test
    public void findOne_validHotelId_returnHotel() {
        Hotel hotel = createHotel();
        Hotel actual = subject.save(hotel);
        assertThat(actual.getHotel_id(), is(notNullValue()));
        assertThat(actual.getItems().size(), is(equalTo(1)));

        Item item = new Item();
        item.setName("Prawn Fry");
        item.setPrice(5.99f);
        item.setVeggie(Boolean.FALSE);
        item.setAvailableTo(22.00f);
        item.setAvailableFrom(8.00f);

        Hotel existingHotel = subject.findOne(actual.getHotel_id());
        existingHotel.getItems().addAll(Collections.singleton(item));
        assertThat(existingHotel.getItems().stream().findFirst().get().isAvaliableAtLunch(), is
                (equalTo(Boolean.TRUE)));
        assertThat(existingHotel.getItems().stream().findFirst().get().isAvaliableAtSnack(), is
                (equalTo(Boolean.FALSE)));
        Hotel updatedHotel = subject.save(existingHotel);
        assertThat(updatedHotel.getHotel_id(), is(actual.getHotel_id()));

        assertThat(updatedHotel.getItems().size(), is(equalTo(2)));

    }

    private Hotel createHotel() {
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
        item.setAvaliableAtBreakfast(Boolean.FALSE);
        item.setAvaliableAtLunch(Boolean.TRUE);
        item.setAvaliableAtSnack(Boolean.FALSE);
        item.setAvaliableAtDinner(Boolean.TRUE);
        item.setAvailableTo(22.00f);
        item.setAvailableFrom(8.00f);

        hotel.getItems().add(item);
        hotel.setAddress(address);
        return hotel;
    }

}