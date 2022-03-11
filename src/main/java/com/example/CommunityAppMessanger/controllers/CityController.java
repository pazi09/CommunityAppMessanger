package com.example.CommunityAppMessanger.controllers;


import com.example.CommunityAppMessanger.models.Cities;
import com.example.CommunityAppMessanger.serviceInterface.CityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityServiceInterface cityService;

    @Autowired
    public CityController(CityServiceInterface cityService){
        this.cityService=cityService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Cities>> getCity() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cities> getCity(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Cities> addCity(@RequestBody Cities cities,@RequestBody String address){
        return cityService.saveCity(cities, address);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Cities> updateCity(@PathVariable(value = "id") Long id, @RequestBody Cities cities) {
        return cityService.updateCity(id, cities);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable(value = "id") Long id) {
        return cityService.deleteById(id);
    }
}
