package com.provider.restaurantservice.adapter.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provider.restaurantservice.RestaurantServiceApplication;
import com.provider.restaurantservice.domain.CuisineType;
import com.provider.restaurantservice.domain.Hotel;
import com.provider.restaurantservice.domain.Item;
import com.provider.restaurantservice.service.HotelService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestaurantServiceApplication.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    private static final String ADD_HOTEL_URI= "/provider/hotels";
    private static final String ADD_ITEMS = "/provider/hotels/1";

    private static final String VALID_HOTEL_REQUEST_JSON = "{\"hotel_id\":1,\"name\":\"Hotel-A\"," +
            "\"address\":{\"id\":1,\"street\":\"Dominian Bvld\",\"streetNumber\":\"12\"," +
            "\"city\":\"GlenAllen\",\"zipcode\":\"23060\",\"state\":\"VA\"},\"items\":[{\"id\":1," +
            "\"name\":\"Fish Fry\",\"isVeggie\":false,\"price\":5.99,\"availableFrom\":8.0," +
            "\"availableTo\":22.0}]}";

    private static final String VALID_ITEM_FOR_HOTEL = "[{\"name\":\"Fish Fry\"," +
            "\"isVeggie\":false,\"price\":5.99,\"availableFrom\":8.0," +
            "\"availableTo\":22.0}]";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelService hotelService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addHotel_withValidData_registerSuccessfully() throws Exception {
        Hotel hotel = new Hotel();
        when(hotelService.createHotel(any())).thenReturn(hotel);
        mockMvc.perform(post(ADD_HOTEL_URI)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(VALID_HOTEL_REQUEST_JSON))
                .andExpect(status().isCreated()).andExpect(content().json(objectMapper
                .writeValueAsString(hotel)));
    }

    @Test
    public void getHotelByName_withValidName_returnHotelWithFullDetails() throws Exception {
        List<Hotel> hotels = new ArrayList();
        hotels.add(new Hotel());
        when(hotelService.getHotelsByName(any())).thenReturn(hotels);
        mockMvc.perform(MockMvcRequestBuilders.get(ADD_HOTEL_URI+"/"+"Hotel-A")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(VALID_HOTEL_REQUEST_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper
                .writeValueAsString(hotels)));
    }

    @Test
    public void addItem_toExistingHotel_saveItemsWithHotel() throws Exception {
        Item item = new Item();
        item.setName("Samosa");
        item.setPrice(2.50f);
        item.setVeggie(true);
        item.setCuisineType(CuisineType.SOUTH_INDIAN);
        Hotel hotel = new Hotel();

        when(hotelService.getHotel(any())).thenReturn(hotel);
        when(hotelService.addItems(hotel)).thenReturn(hotel);
        mockMvc.perform(post(ADD_ITEMS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(VALID_ITEM_FOR_HOTEL))
                .andExpect(status().isCreated());
    }
}