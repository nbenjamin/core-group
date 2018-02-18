package com.provider.restaurantservice.adapter.repository;

import com.provider.restaurantservice.domain.Hotel;

import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface HotelRepository extends CrudRepository<Hotel, Integer> {

    Hotel save(Hotel hotel);

    Hotel findByName(String name);

}
