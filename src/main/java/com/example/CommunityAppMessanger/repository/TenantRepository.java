package com.example.CommunityAppMessanger.repository;

import com.example.CommunityAppMessanger.models.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TenantRepository extends JpaRepository<Tenants,Long> {
}
