package com.provider.restaurantservice.adapter.api;

import com.provider.restaurantservice.domain.Hotel;
import com.provider.restaurantservice.domain.Item;
import com.provider.restaurantservice.service.HotelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/provider")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotels")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) throws Exception {
        Hotel temp = hotelService.createHotel(hotel);
        return Optional.ofNullable(hotelService.createHotel(hotel)).map(h -> ResponseEntity.status
                (HttpStatus.CREATED).body(h)).orElseThrow(() -> new Exception("Unable to " +
               "register Hotel " +
               "now"));
    }

    @GetMapping("/hotels/{name}")
    public ResponseEntity<List<Hotel>> getHotelByName(@PathVariable("name") String name)  throws
            Exception {
        return Optional.ofNullable(hotelService.getHotelsByName(name)).map(h-> ResponseEntity
                .status(HttpStatus.OK).body(h)).orElseThrow(() -> new Exception("Unable to find " +
                "Hotel with this name"));
    }

    @PostMapping("/hotels/{id}")
    public ResponseEntity<Hotel> addItems(@PathVariable("id") Integer id, @RequestBody List<Item>
            items) throws Exception {
        Hotel hotel = Optional.ofNullable(hotelService.getHotel(id)).orElseThrow(() ->
                new Exception("Unable to find Hotel"));
        hotel.getItems().addAll(items);
        return Optional.ofNullable(hotelService.addItems(hotel)).map(h -> ResponseEntity.status
                (HttpStatus.CREATED).body(h)).orElseThrow(() -> new Exception("Unable to save " +
                "Items now"));
    }
}
