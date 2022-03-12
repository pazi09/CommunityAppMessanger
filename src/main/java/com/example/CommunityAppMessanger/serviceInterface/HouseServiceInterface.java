package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.House;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface HouseServiceInterface {
    ResponseEntity<House> saveHouse(House house);

    ResponseEntity<List<House>> findAll();

    ResponseEntity<House> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<House> updateHouse(Long id, House house);

    House getHouse(String address);

    ResponseEntity<House> getHouseByUser(Authentication authentication);
}
