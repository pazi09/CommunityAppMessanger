package com.example.CommunityAppMessanger.services;

import com.example.CommunityAppMessanger.models.Flats;
import com.example.CommunityAppMessanger.models.Tenants;
import com.example.CommunityAppMessanger.repository.FlatRepository;
import com.example.CommunityAppMessanger.serviceInterface.FlatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlatService implements FlatServiceInterface {

    private final FlatRepository flatRepository;

    @Autowired
    public FlatService(FlatRepository flatRepository){
        this.flatRepository=flatRepository;
    }

    @Override
    public ResponseEntity<Flats> saveFlat(Flats flats) {
        try {
            Flats newFlat=flatRepository.save(new Flats(flats.getFlatNumber(),flats.getRooms(),flats.getSquare()));
            return new ResponseEntity<>(newFlat, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Flats>> findAll() {
        try {
            List<Flats> flats = new ArrayList<Flats>();
            flatRepository.findAll().forEach(flats::add);
            if (flats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(flats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Flats> findById (Long Id) {
        Optional<Flats> flats = flatRepository.findById(Id);

        if (flats.isPresent()) {
            return new ResponseEntity<>(flats.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        try {
            flatRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Flats> updateFlat(Long id, Flats flat) {
        Optional<Flats> flatDB = flatRepository.findById(id);

        if (flatDB.isPresent()) {
            Flats _flat = flatDB.get();
            _flat.setSquare(flat.getSquare());
            _flat.setRooms(flat.getRooms());

            return new ResponseEntity<>(flatRepository.save(_flat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
