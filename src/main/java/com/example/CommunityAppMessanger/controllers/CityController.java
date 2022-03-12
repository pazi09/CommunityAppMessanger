package com.example.CommunityAppMessanger.controllers;


import com.example.CommunityAppMessanger.models.City;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.CityServiceInterface;
import com.example.CommunityAppMessanger.utils.CityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityServiceInterface cityService;

    @Autowired
    public CityController(CityServiceInterface cityService){
        this.cityService=cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<City> addCity(@RequestBody City city, Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        ResponseEntity<City> citiesResponseEntity = cityService.saveCity(city);
        CityHolder.saveCityForUser(userId,citiesResponseEntity.getBody().getId());
        return citiesResponseEntity;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<City> updateCity(@PathVariable(value = "id") Long id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable(value = "id") Long id) {
        return cityService.deleteById(id);
    }

    @GetMapping(path = "")
    public ResponseEntity<City> getCurrentCity(Authentication authentication) {
        return cityService.getCityByUser(authentication);
    }
}
