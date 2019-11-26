package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
