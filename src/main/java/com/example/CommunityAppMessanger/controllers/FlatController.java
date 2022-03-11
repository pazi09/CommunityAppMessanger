package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.Flats;
import com.example.CommunityAppMessanger.models.Tenants;
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
import java.util.Set;

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

    @GetMapping(path = "")
    public ResponseEntity<List<Flats>> getFlats() {
        return flatService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flats> getFlat(@PathVariable("id") Long id) {
        return flatService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Flats> addFlat(@RequestBody Flats flat, Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        Long houseByUserId = HouseHolder.getHouseByUserId(userId);
        flat.setHouse(houseService.findById(houseByUserId).getBody());
        ResponseEntity<List<Tenants>> tenantsByUserId = tenantService.findByUserId(userId);
        if(tenantsByUserId.getBody()!=null)
            flat.setTenants(new HashSet<>(tenantsByUserId.getBody()));
        return flatService.saveFlat(flat);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Flats> updateFlat(@PathVariable(value = "id") Long id, @RequestBody Flats flat) {
        return flatService.updateFlat(id, flat);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteFlat(@PathVariable(value = "id") Long id) {
        return flatService.deleteById(id);
    }
}
