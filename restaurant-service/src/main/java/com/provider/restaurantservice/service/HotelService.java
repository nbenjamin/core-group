package com.provider.restaurantservice.service;

import com.provider.restaurantservice.adapter.repository.HotelRepository;
import com.provider.restaurantservice.domain.Hotel;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Hotel createHotel(Hotel hotel) {
       return hotelRepository.save(hotel);
    }

    public Hotel getHotelByName(String name) {
        return hotelRepository.findByName(name);
    }

    public Hotel addItems(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel getHotel(Integer id) {
        return hotelRepository.findOne(id);
    }

    public List<Hotel> getHotelsByName(String name) {
        return hotelRepository.findByNameEquals(name);
    }

}
