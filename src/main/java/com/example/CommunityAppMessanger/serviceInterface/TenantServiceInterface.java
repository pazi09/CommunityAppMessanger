package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.Tenant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TenantServiceInterface {
    ResponseEntity<Tenant> saveTenant(Tenant tenant);

    ResponseEntity<List<Tenant>> findAll();

    ResponseEntity<Tenant> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<Tenant> updateTenant(Long id, Tenant tenant);

    ResponseEntity<List<Tenant>> findByUserId(Long userId);

    ResponseEntity<Tenant> getTenantByUser(Authentication authentication);
}
