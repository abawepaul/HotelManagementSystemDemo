package com.hms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.exception.ResourceNotFoundException;
import com.hms.model.Hotel;
import com.hms.repository.HotelRepository;

@RestController
@RequestMapping("/api")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    // Get All Notes
    @GetMapping("/hotels")
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    

    // Create a new Note
    @PostMapping("/hotels")
    public Hotel createHotel(@Valid @RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Get a Single Note
    @GetMapping("/hotels/{id}")
    public Hotel getHotelById(@PathVariable(value = "id") Long hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", hotelId));
    }
    

    // Update a Note
    @PutMapping("/hotels/{id}")
    public Hotel updateHotel(@PathVariable(value = "id") Long hotelId,
                                            @Valid @RequestBody Hotel hotelDetails) {

    	Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", hotelId));

    	hotel.setHotelName(hotelDetails.getHotelName());
    	hotel.setHotelAddress(hotelDetails.getHotelAddress());

        Hotel updatedHotel = hotelRepository.save(hotel);
        return updatedHotel;
    }

    // Delete a Note
    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable(value = "id") Long hotelId) {
    	Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", hotelId));

    	hotelRepository.delete(hotel);

        return ResponseEntity.ok().build();
    }
    
}
