package com.provider.restaurantservice.adapter.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provider.restaurantservice.RestaurantServiceApplication;
import com.provider.restaurantservice.domain.Hotel;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestaurantServiceApplication.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    private static final String ADD_HOTEL_URI= "/provider/hotel";

    private static final String VALID_HOTEL_REQUEST_JSON = "{\"hotel_id\":1,\"name\":\"Hotel-A\"," +
            "\"address\":{\"id\":1,\"street\":\"Dominian Bvld\",\"streetNumber\":\"12\"," +
            "\"city\":\"GlenAllen\",\"zipcode\":\"23060\",\"state\":\"VA\"},\"items\":[{\"id\":1," +
            "\"name\":\"Fish Fry\",\"isVeggie\":false,\"price\":5.99,\"availableFrom\":8.0," +
            "\"availableTo\":22.0}]}";

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
        Hotel hotel = new Hotel();
        when(hotelService.getHotelByName(any())).thenReturn(hotel);
        mockMvc.perform(MockMvcRequestBuilders.get(ADD_HOTEL_URI+"/"+"Hotel-A")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(VALID_HOTEL_REQUEST_JSON))
                .andExpect(status().isOk()).andExpect(content().json(objectMapper
                .writeValueAsString(hotel)));
    }
}