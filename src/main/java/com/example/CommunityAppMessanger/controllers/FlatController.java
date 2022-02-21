package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.Flats;
import com.example.CommunityAppMessanger.serviceInterface.FlatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/flats")
public class FlatController {

    private final FlatServiceInterface flatService;

    @Autowired
    public FlatController(FlatServiceInterface flatService) {
        this.flatService = flatService;
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
    public ResponseEntity<Flats> addFlat(@RequestBody Flats flat){
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
