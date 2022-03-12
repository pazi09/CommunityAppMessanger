package com.example.CommunityAppMessanger.services;


import com.example.CommunityAppMessanger.models.City;
import com.example.CommunityAppMessanger.repository.CityRepository;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.CityServiceInterface;
import com.example.CommunityAppMessanger.utils.CityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<City> saveCity(City city) {
        try {
            City cityByCity = cityRepository.findByCity(city.getCity());
            if(cityByCity!=null){
                return new ResponseEntity<>(cityByCity,HttpStatus.NOT_MODIFIED);
            }
            City newCity=cityRepository.save(city);
            return new ResponseEntity<>(newCity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<City>> findAll() {
        try {
            List<City> cities = new ArrayList<City>();
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
    public ResponseEntity<City> findById (Long Id) {
        Optional<City> cities = cityRepository.findById(Id);

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
    public ResponseEntity<City> updateCity(Long id, City city) {
        Optional<City> cityDB = cityRepository.findById(id);

        if (cityDB.isPresent()) {
            City _city = cityDB.get();
            _city.setCity(city.getCity());

            return new ResponseEntity<>(cityRepository.save(_city), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<City> getCityByUser(Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        Long cityByUserId = CityHolder.getCityByUserId(userId);
        ResponseEntity<City> city= new ResponseEntity<>(cityRepository.findById(cityByUserId).get(),HttpStatus.OK);
        return city;
    }
}
