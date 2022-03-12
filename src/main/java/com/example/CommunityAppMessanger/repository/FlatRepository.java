package com.example.CommunityAppMessanger.repository;

import com.example.CommunityAppMessanger.models.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepository extends JpaRepository<Flat,Long> {
    Flat findByFlatNumber(Long flatNumber);

    Flat findByHouseId(Long houseId);
}
