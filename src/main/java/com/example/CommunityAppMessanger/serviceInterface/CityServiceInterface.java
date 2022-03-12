package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CityServiceInterface {
    ResponseEntity<City> saveCity(City city);

    ResponseEntity<List<City>> findAll();

    ResponseEntity<City> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<City> updateCity(Long id, City city);

    ResponseEntity<City> getCityByUser(Authentication authentication);
}
