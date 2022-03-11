package com.example.CommunityAppMessanger.services;


import com.example.CommunityAppMessanger.models.Cities;
import com.example.CommunityAppMessanger.models.Flats;
import com.example.CommunityAppMessanger.models.Houses;
import com.example.CommunityAppMessanger.repository.CityRepository;
import com.example.CommunityAppMessanger.serviceInterface.CityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityService implements CityServiceInterface {

    private final CityRepository cityRepository;
    private final HouseService houseService;

    @Autowired
    public  CityService(CityRepository cityRepository,HouseService houseService){
        this.cityRepository=cityRepository;this.houseService=houseService;
    }

    @Override
    public ResponseEntity<Cities> saveCity(Cities city, String address) {
        try {
            Cities newCity=cityRepository.save(new Cities(city.getCity(),new HashSet<>()));
            Set<Houses> houses= newCity.getHouses();
            houses.add(houseService.getHouse(address));
            newCity.setHouses(houses);
            cityRepository.save(newCity);
            return new ResponseEntity<>(newCity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Cities>> findAll() {
        try {
            List<Cities> cities = new ArrayList<Cities>();
            cityRepository.findAll().forEach(cities::add);
            if (cities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Cities> findById (Long Id) {
        Optional<Cities> cities = cityRepository.findById(Id);

        if (cities.isPresent()) {
            return new ResponseEntity<>(cities.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        try {
            cityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Cities> updateCity(Long id, Cities cities) {
        Optional<Cities> cityDB = cityRepository.findById(id);

        if (cityDB.isPresent()) {
            Cities _cities = cityDB.get();
            _cities.setCity(cities.getCity());

            return new ResponseEntity<>(cityRepository.save(_cities), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
