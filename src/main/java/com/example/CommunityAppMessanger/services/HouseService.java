package com.example.CommunityAppMessanger.services;

import com.example.CommunityAppMessanger.models.Flats;
import com.example.CommunityAppMessanger.models.Houses;
import com.example.CommunityAppMessanger.models.Tenants;
import com.example.CommunityAppMessanger.repository.HouseRepository;
import com.example.CommunityAppMessanger.serviceInterface.HouseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HouseService implements HouseServiceInterface {

    private final HouseRepository houseRepository;
    private final FlatService flatService;

    @Autowired
    public HouseService(HouseRepository houseRepository,FlatService flatService){
        this.houseRepository=houseRepository;
        this.flatService=flatService;
    }

    @Override
    public ResponseEntity<Houses> saveHouse(Houses houses) {
        try {
            Houses houseByAddress = houseRepository.findByAddress(houses.getAddress());
            if(houseByAddress!= null){
                return new ResponseEntity<>(houseByAddress, HttpStatus.NOT_MODIFIED);
            }
            Houses newHouse= houseRepository.save(houses);
            return new ResponseEntity<>(newHouse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Houses>> findAll() {
        try {
            List<Houses> houses = new ArrayList<>();
            houseRepository.findAll().forEach(houses::add);
            if (houses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(houses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Houses> findById (Long Id) {
        Optional<Houses> houses = houseRepository.findById(Id);

        if (houses.isPresent()) {
            return new ResponseEntity<>(houses.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        try {
            houseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Houses> updateHouse(Long id, Houses house) {
        Optional<Houses> houseDB = houseRepository.findById(id);

        if (houseDB.isPresent()) {
            Houses _house = houseDB.get();
            _house.setAddress(house.getAddress());

            return new ResponseEntity<>(houseRepository.save(_house), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public Houses getHouse(String address){
        return houseRepository.findByAddress(address);
    }
}
