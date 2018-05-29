package com.provider.restaurantservice.service;

import com.provider.restaurantservice.adapter.repository.HotelRepository;
import com.provider.restaurantservice.adapter.repository.ItemRepository;
import com.provider.restaurantservice.domain.Hotel;
import com.provider.restaurantservice.domain.Item;

import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

  private HotelRepository hotelRepository;
  private ItemRepository itemRepository;

  public HotelService(HotelRepository hotelRepository, ItemRepository itemRepository) {
    this.hotelRepository = hotelRepository;
    this.itemRepository = itemRepository;
  }

  public Hotel createHotel(Hotel hotel) {
    return hotelRepository.save(hotel);
  }

  public Hotel getHotelByName(String name) {
    return hotelRepository.findByNameIgnoreCase(name);
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

  // Item


  public Item addItems(Item item) {
    return itemRepository.save(item);
  }

  public List<Item> getItemsByHotelId(Integer hotelId) {
    return itemRepository.findByHotelId(hotelId);
  }

}
