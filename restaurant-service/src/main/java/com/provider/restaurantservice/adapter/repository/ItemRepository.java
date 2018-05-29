package com.provider.restaurantservice.adapter.repository;

import com.provider.restaurantservice.domain.Item;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ItemRepository extends CrudRepository<Item, Integer> {

  List<Item> findByHotelId(Integer hotelId);
}
