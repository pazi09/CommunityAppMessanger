package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.Tenants;
import com.example.CommunityAppMessanger.serviceInterface.TenantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final TenantServiceInterface tenantService;

    @Autowired
    public TenantController(TenantServiceInterface tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Tenants>> getTenants() {
        return tenantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenants> getTenant(@PathVariable("id") Long id) {
        return tenantService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Tenants> addTenant(@RequestBody Tenants tenant){
        return tenantService.saveTenant(tenant);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Tenants> updateTenant(@PathVariable(value = "id") Long id, @RequestBody Tenants tenant) {
        return tenantService.updateTenant(id, tenant);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTenant(@PathVariable(value = "id") Long id) {
        return tenantService.deleteById(id);
    }
}
