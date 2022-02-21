package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.Flats;
import com.example.CommunityAppMessanger.models.Tenants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FlatServiceInterface {
    ResponseEntity<Flats> saveFlat(Flats flats);

    ResponseEntity<List<Flats>> findAll();

    ResponseEntity<Flats> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<Flats> updateFlat(Long id, Flats flats);
}
