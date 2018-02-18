package com.provider.restaurantservice.adapter.api;

import com.provider.restaurantservice.domain.Hotel;
import com.provider.restaurantservice.service.HotelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/provider")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) throws Exception {
        Hotel temp = hotelService.createHotel(hotel);
        return Optional.ofNullable(hotelService.createHotel(hotel)).map(h -> ResponseEntity.status
                (HttpStatus.CREATED).body(h)).orElseThrow(() -> new Exception("Unable to " +
               "register Hotel " +
               "now"));
    }

    @GetMapping("/hotel/{name}")
    public ResponseEntity<Hotel> getHotelByName(@PathVariable("name") String name)  throws
            Exception {
        return Optional.ofNullable(hotelService.getHotelByName(name)).map(h-> ResponseEntity
                .status(HttpStatus.OK).body(h)).orElseThrow(() -> new Exception("Unable to find " +
                "Hotel with this name"));
    }
}
