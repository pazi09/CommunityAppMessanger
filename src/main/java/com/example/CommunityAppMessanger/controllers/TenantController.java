package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.Tenants;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.TenantServiceInterface;
import com.example.CommunityAppMessanger.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final TenantServiceInterface tenantService;
    private final UserServiceInterface userService;

    @Autowired
    public TenantController(TenantServiceInterface tenantService,UserServiceInterface userService) {
        this.tenantService = tenantService;
        this.userService = userService;
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
    public ResponseEntity<Tenants> addTenant(@RequestBody Tenants tenant, Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        tenant.setUser(userService.findById(userId).getBody());
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
