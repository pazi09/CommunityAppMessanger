package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.Cities;
import com.example.CommunityAppMessanger.models.Houses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityServiceInterface {
    ResponseEntity<Cities> saveCity(Cities city);

    ResponseEntity<List<Cities>> findAll();

    ResponseEntity<Cities> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<Cities> updateCity(Long id, Cities city);
}
