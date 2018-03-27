package com.provider.restaurantservice.adapter.repository;

import com.provider.restaurantservice.domain.Hotel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 */
public interface HotelRepository extends CrudRepository<Hotel, Integer> {

    Hotel save(Hotel hotel);

    Hotel findByNameIgnoreCase(String name);

    List<Hotel> findByNameEqualsIgnoreCase(String name);
    
    Hotel findByEmailIgnoreCase(String email);
    
}
