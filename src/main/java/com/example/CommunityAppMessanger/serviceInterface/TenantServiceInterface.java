package com.example.CommunityAppMessanger.serviceInterface;

import com.example.CommunityAppMessanger.models.Tenants;

import com.example.CommunityAppMessanger.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TenantServiceInterface {
    ResponseEntity<Tenants> saveTenant(Tenants tenant);

    ResponseEntity<List<Tenants>> findAll();

    ResponseEntity<Tenants> findById (Long Id);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<Tenants> updateTenant(Long id, Tenants tenant);
}
