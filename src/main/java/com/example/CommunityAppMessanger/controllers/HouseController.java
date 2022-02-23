package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.Flats;
import com.example.CommunityAppMessanger.models.Houses;
import com.example.CommunityAppMessanger.serviceInterface.HouseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseServiceInterface houseService;

    @Autowired
    public HouseController(HouseServiceInterface houseService){
        this.houseService=houseService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Houses>> getHouses() {
        return houseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Houses> getHouse(@PathVariable("id") Long id) {
        return houseService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Houses> addHouse(@RequestBody Houses house){
        return houseService.saveHouse(house);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Houses> updateHouse(@PathVariable(value = "id") Long id, @RequestBody Houses house) {
        return houseService.updateHouse(id, house);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteHouse(@PathVariable(value = "id") Long id) {
        return houseService.deleteById(id);
    }
}
