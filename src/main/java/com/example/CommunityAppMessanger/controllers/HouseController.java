package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.Houses;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.HouseServiceInterface;
import com.example.CommunityAppMessanger.utils.HouseHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Houses> addHouse(@RequestBody Houses house, Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        ResponseEntity<Houses> housesResponseEntity = houseService.saveHouse(house);
        HouseHolder.saveHouseForUser(userId,housesResponseEntity.getBody().getId());
        return housesResponseEntity;
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
