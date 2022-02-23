package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.Flats;
import com.example.CommunityAppMessanger.models.Houses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HouseServiceInterface {
    ResponseEntity<Houses> saveHouse(Houses houses);

    ResponseEntity<List<Houses>> findAll();

    ResponseEntity<Houses> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<Houses> updateHouse(Long id, Houses houses);
}
