package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.Flat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FlatServiceInterface {
    ResponseEntity<Flat> saveFlat(Flat flat);

    ResponseEntity<List<Flat>> findAll();

    ResponseEntity<Flat> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<Flat> updateFlat(Long id, Flat flat);

    ResponseEntity<Flat> getFlatByHouse(Authentication authentication);
}
