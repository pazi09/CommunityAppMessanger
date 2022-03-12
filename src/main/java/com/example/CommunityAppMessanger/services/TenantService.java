package com.example.CommunityAppMessanger.services;

import com.example.CommunityAppMessanger.models.Tenant;
import com.example.CommunityAppMessanger.repository.TenantRepository;
import com.example.CommunityAppMessanger.security.services.UserDetailsImpl;
import com.example.CommunityAppMessanger.serviceInterface.TenantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService implements TenantServiceInterface {

    private final TenantRepository tenantRepository;


    @Autowired
    public TenantService(TenantRepository tenantRepository){
        this.tenantRepository=tenantRepository;

    }

    @Override
    public ResponseEntity<Tenant> saveTenant(Tenant tenant) {
        try {
           Tenant newTenant= tenantRepository.save(tenant);
           return new ResponseEntity<>(newTenant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Tenant>> findAll() {
        try {
            List<Tenant> tenants = new ArrayList<Tenant>();
            tenantRepository.findAll().forEach(tenants::add);
            if (tenants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tenants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Tenant> findById (Long Id) {
        Optional<Tenant> tenants = tenantRepository.findById(Id);

        if (tenants.isPresent()) {
            return new ResponseEntity<>(tenants.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        try {
            tenantRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Tenant> updateTenant(Long id, Tenant tenant) {
        Optional<Tenant> tenantDB = tenantRepository.findById(id);

        if (tenantDB.isPresent()) {
            Tenant _tenant = tenantDB.get();
            _tenant.setTenantName(tenant.getTenantName());
            _tenant.setTenantSecondName(tenant.getTenantSecondName());
            _tenant.setTenantLastName(tenant.getTenantLastName());
            return new ResponseEntity<>(tenantRepository.save(_tenant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Tenant>> findByUserId(Long userId){
        return new ResponseEntity<>(tenantRepository.findTenantsByUserId(userId),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Tenant> getTenantByUser(Authentication authentication){
        Long userId=((UserDetailsImpl)authentication.getPrincipal()).getId();
        Tenant tenantByUserId = tenantRepository.findTenantByUserId(userId);
        return new ResponseEntity<>(tenantByUserId,HttpStatus.OK);
    }
}
