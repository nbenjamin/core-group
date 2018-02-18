package com.provider.restaurantservice.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import com.provider.restaurantservice.adapter.repository.HotelRepository;
import com.provider.restaurantservice.domain.Address;
import com.provider.restaurantservice.domain.Hotel;
import com.provider.restaurantservice.domain.Item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {

    @InjectMocks
    private HotelService subject;

    @Mock
    private HotelRepository hotelRepository;

    @Test
    public void createHotel_withValidHotel_retrunSuccess() {
        Hotel hotel = createHotel();
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        Hotel actual = subject.createHotel(hotel);
        assertThat(actual, is(notNullValue()));

    }

    @Test
    public void getHotelByName_withValidName_returnHotelSuccessfull() {

    }

    private Hotel createHotel() {
        Hotel hotel = new Hotel();

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

        hotel.setAddress(address);
        hotel.getItems().add(item);

        return hotel;
    }

}