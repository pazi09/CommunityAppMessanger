package com.example.CommunityAppMessanger.services;

import com.example.CommunityAppMessanger.models.Tenants;
import com.example.CommunityAppMessanger.models.User;
import com.example.CommunityAppMessanger.repository.TenantRepository;
import com.example.CommunityAppMessanger.repository.UserRepository;
import com.example.CommunityAppMessanger.serviceInterface.TenantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Tenants> saveTenant(Tenants tenant) {
        try {
           Tenants newTenant= tenantRepository.
                   save(new Tenants(tenant.getTenantName(),tenant.getTenantSecondName(),tenant.getTenantLastName(),tenant.getUser()));
           return new ResponseEntity<>(newTenant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Tenants>> findAll() {
        try {
            List<Tenants> tenants = new ArrayList<Tenants>();
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
    public ResponseEntity<Tenants> findById (Long Id) {
        Optional<Tenants> tenants = tenantRepository.findById(Id);

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
    public ResponseEntity<Tenants> updateTenant(Long id, Tenants tenant) {
        Optional<Tenants> tenantDB = tenantRepository.findById(id);

        if (tenantDB.isPresent()) {
            Tenants _tenant = tenantDB.get();
            _tenant.setTenantName(tenant.getTenantName());
            _tenant.setTenantSecondName(tenant.getTenantSecondName());
            _tenant.setTenantLastName(tenant.getTenantLastName());
            return new ResponseEntity<>(tenantRepository.save(_tenant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
