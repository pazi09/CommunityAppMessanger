package com.example.CommunityAppMessanger.controllers;

import com.example.CommunityAppMessanger.models.Tenant;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.TenantServiceInterface;
import com.example.CommunityAppMessanger.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenant(@PathVariable("id") Long id) {
        return tenantService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Tenant> addTenant(@RequestBody Tenant tenant, Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        tenant.setUser(userService.findById(userId).getBody());
        return tenantService.saveTenant(tenant);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable(value = "id") Long id, @RequestBody Tenant tenant) {
        return tenantService.updateTenant(id, tenant);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTenant(@PathVariable(value = "id") Long id) {
        return tenantService.deleteById(id);
    }

    @GetMapping(path = "")
    public ResponseEntity<Tenant> getCurrentHouse(Authentication authentication) {
        return tenantService.getTenantByUser(authentication);
    }
}
