package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.House;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.CityServiceInterface;
import com.example.CommunityAppMessanger.serviceInterface.HouseServiceInterface;
import com.example.CommunityAppMessanger.utils.CityHolder;
import com.example.CommunityAppMessanger.utils.HouseHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseServiceInterface houseService;
    private final CityServiceInterface cityService;

    @Autowired
    public HouseController(HouseServiceInterface houseService,CityServiceInterface cityService){
        this.houseService=houseService;
        this.cityService=cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouse(@PathVariable("id") Long id) {
        return houseService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<House> addHouse(@RequestBody House house, Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        Long cityByUserId = CityHolder.getCityByUserId(userId);
        house.setCity(cityService.findById(cityByUserId).getBody());
        ResponseEntity<House> housesResponseEntity = houseService.saveHouse(house);
        HouseHolder.saveHouseForUser(userId,housesResponseEntity.getBody().getId());

        return housesResponseEntity;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable(value = "id") Long id, @RequestBody House house) {
        return houseService.updateHouse(id, house);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteHouse(@PathVariable(value = "id") Long id) {
        return houseService.deleteById(id);
    }

    @GetMapping(path = "")
    public ResponseEntity<House> getCurrentHouse(Authentication authentication) {
        return houseService.getHouseByUser(authentication);
    }
}
