package com.example.CommunityAppMessanger.repository;

import com.example.CommunityAppMessanger.models.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
    House findByAddress(String address);
}
