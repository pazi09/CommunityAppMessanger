package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.Flat;
import com.example.CommunityAppMessanger.models.Tenant;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.FlatServiceInterface;
import com.example.CommunityAppMessanger.serviceInterface.HouseServiceInterface;
import com.example.CommunityAppMessanger.serviceInterface.TenantServiceInterface;
import com.example.CommunityAppMessanger.utils.HouseHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/flats")
public class FlatController {

    private final FlatServiceInterface flatService;
    private final HouseServiceInterface houseService;
    private final TenantServiceInterface tenantService;

    @Autowired
    public FlatController(FlatServiceInterface flatService,HouseServiceInterface houseService,TenantServiceInterface tenantService) {
        this.flatService = flatService;
        this.houseService = houseService;
        this.tenantService = tenantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flat> getFlat(@PathVariable("id") Long id) {
        return flatService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Flat> addFlat(@RequestBody Flat flat, Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        Long houseByUserId = HouseHolder.getHouseByUserId(userId);
        flat.setHouse(houseService.findById(houseByUserId).getBody());
        ResponseEntity<List<Tenant>> tenantsByUserId = tenantService.findByUserId(userId);
        if(tenantsByUserId.getBody()!=null)
            flat.setTenants(new HashSet<>(tenantsByUserId.getBody()));
        return flatService.saveFlat(flat);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Flat> updateFlat(@PathVariable(value = "id") Long id, @RequestBody Flat flat) {
        return flatService.updateFlat(id, flat);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteFlat(@PathVariable(value = "id") Long id) {
        return flatService.deleteById(id);
    }

    @GetMapping(path = "")
    public ResponseEntity<Flat> getCurrentFlat(Authentication authentication) {
        return flatService.getFlatByHouse(authentication);
    }
}
