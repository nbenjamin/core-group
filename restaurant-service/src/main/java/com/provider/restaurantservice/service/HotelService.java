package com.provider.restaurantservice.service;

import com.provider.restaurantservice.adapter.repository.HotelRepository;
import com.provider.restaurantservice.domain.Hotel;

import java.util.Optional;
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
    return hotelRepository.findByNameIgnoreCase(name);
  }

  public Hotel addItems(Hotel hotel) {
    return hotelRepository.save(hotel);
  }

  public Hotel getHotel(Integer id) {
    return hotelRepository.findOne(id);
  }

  public List<Hotel> getHotelsByName(String name) {
    return hotelRepository.findByNameEqualsIgnoreCase(name);
  }

  public Hotel findByEmail(String email) {
    return hotelRepository.findByEmailIgnoreCase(email);
  }

  public  Optional<Hotel> findById(Integer id ) {
    return Optional.ofNullable(hotelRepository.findOne(id));
  }

}
