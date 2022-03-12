package com.example.CommunityAppMessanger.repository;

import com.example.CommunityAppMessanger.models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long> {
    List<Tenant> findTenantsByUserId(Long userId);

    Tenant findTenantByUserId(Long userId);
}
