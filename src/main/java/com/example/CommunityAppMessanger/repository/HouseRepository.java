package com.example.CommunityAppMessanger.repository;

import com.example.CommunityAppMessanger.models.Houses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<Houses,Long> {
}
