package com.example.CommunityAppMessanger.services;

import com.example.CommunityAppMessanger.models.House;
import com.example.CommunityAppMessanger.repository.HouseRepository;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.HouseServiceInterface;
import com.example.CommunityAppMessanger.utils.HouseHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<House> saveHouse(House house) {
        try {
            House houseByAddress = houseRepository.findByAddress(house.getAddress());
            if(houseByAddress!= null){
                return new ResponseEntity<>(houseByAddress, HttpStatus.NOT_MODIFIED);
            }
            House newHouse= houseRepository.save(house);
            return new ResponseEntity<>(newHouse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<House>> findAll() {
        try {
            List<House> houses = new ArrayList<>();
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
    public ResponseEntity<House> findById (Long Id) {
        Optional<House> houses = houseRepository.findById(Id);

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
    public ResponseEntity<House> updateHouse(Long id, House house) {
        Optional<House> houseDB = houseRepository.findById(id);

        if (houseDB.isPresent()) {
            House _house = houseDB.get();
            _house.setAddress(house.getAddress());

            return new ResponseEntity<>(houseRepository.save(_house), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public House getHouse(String address){
        return houseRepository.findByAddress(address);
    }

    @Override
    public ResponseEntity<House> getHouseByUser(Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        Long houseByUserId = HouseHolder.getHouseByUserId(userId);
        ResponseEntity<House> house= new ResponseEntity<>(houseRepository.findById(houseByUserId).get(),HttpStatus.OK);
        return house;
    }
}
