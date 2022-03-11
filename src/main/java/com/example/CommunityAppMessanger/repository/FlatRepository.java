package com.example.CommunityAppMessanger.repository;

import com.example.CommunityAppMessanger.models.Flats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepository extends JpaRepository<Flats,Long> {
    Flats findByFlatNumber(Long flatNumber);
}
