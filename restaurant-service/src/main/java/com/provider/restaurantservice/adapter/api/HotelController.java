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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

@RestController
@RequestMapping("/provider")
public class HotelController {

  private HotelService hotelService;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @PostMapping("/hotels")
  public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) throws Exception {
    return Optional.ofNullable(hotelService.createHotel(hotel)).map(h -> ResponseEntity.status
        (HttpStatus.CREATED).body(h)).orElseThrow(() -> new Exception("Unable to " +
        "register Hotel " +
        "now"));
  }


  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Integer login(@RequestBody Hotel login) throws Exception {

    if (login.getEmail() == null || login.getPassword() == null) {
      throw new Exception("Please provide in username and password");
    }
    String email = login.getEmail();
    String password = login.getPassword();
    Hotel user = Optional.ofNullable(hotelService.findByEmail(email)).orElseThrow(() ->
        new Exception("Unable to find Hotel"));
    String pwd = user.getPassword();
    if (!password.equals(pwd)) {
      throw new Exception("Invalid login. Please check your name and password.");
    }
    return user.getHotel_id();
  }


  @GetMapping("/hotels")
  public ResponseEntity<List<Hotel>> getHotelsByName(@RequestParam("name") String name) throws
      Exception {
    return Optional.ofNullable(hotelService.getHotelsByName(name)).map(h -> ResponseEntity
        .status(HttpStatus.OK).body(h)).orElseThrow(() -> new Exception("Unable to find " +
        "Hotel with this name"));
  }


  @GetMapping("/hotels/{id}")
  public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Integer id) throws
      Exception {
    return Optional.ofNullable(hotelService.findById(id)).filter(hotel -> hotel.isPresent())
        .map(h -> ResponseEntity
            .status(HttpStatus.OK).body(h.get()))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PostMapping("/hotels/{id}/items")
  public ResponseEntity<Hotel> addItems(@PathVariable("id") Integer id, @RequestBody List<Item>
      items) throws Exception {
    Hotel hotel = Optional.ofNullable(hotelService.getHotel(id)).orElseThrow(() ->
        new Exception("Unable to find Hotel"));
    return Optional.ofNullable(hotelService.addItems(hotel)).map(h -> ResponseEntity.status
        (HttpStatus.CREATED).body(h)).orElseThrow(() -> new Exception("Unable to save " +
        "Items now"));
  }
}
