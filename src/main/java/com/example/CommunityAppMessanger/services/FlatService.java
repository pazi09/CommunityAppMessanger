package com.example.CommunityAppMessanger.services;

import com.example.CommunityAppMessanger.models.City;
import com.example.CommunityAppMessanger.models.Flat;
import com.example.CommunityAppMessanger.repository.FlatRepository;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.FlatServiceInterface;
import com.example.CommunityAppMessanger.utils.HouseHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Flat> saveFlat(Flat flat) {
        try {

            List<Flat> flats = new ArrayList<Flat>();
            flatRepository.findAll().forEach(flats::add);
            if (flats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Flat newFlat=flatRepository.save(flat);
            return new ResponseEntity<>(newFlat, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Flat>> findAll() {
        try {
            List<Flat> flats = new ArrayList<Flat>();
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
    public ResponseEntity<Flat> findById (Long Id) {
        Optional<Flat> flats = flatRepository.findById(Id);

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
    public ResponseEntity<Flat> updateFlat(Long id, Flat flat) {
        Optional<Flat> flatDB = flatRepository.findById(id);

        if (flatDB.isPresent()) {
            Flat _flat = flatDB.get();
            _flat.setFlatNumber(flat.getFlatNumber());

            return new ResponseEntity<>(flatRepository.save(_flat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Flat> getFlatByHouse(Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        Long houseByUserId = HouseHolder.getHouseByUserId(userId);
        Flat flatByHouseId = flatRepository.findByHouseId(houseByUserId);
        return new ResponseEntity<>(flatByHouseId,HttpStatus.OK);
    }

     Flat getFlat(Long flatNumber){
        return flatRepository.findByFlatNumber(flatNumber);
    }


}
