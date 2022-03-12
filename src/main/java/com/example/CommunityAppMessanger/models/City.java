package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table( name = "city",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "city")
})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    public City() {
    }


    public City(String city){
        this.city=city;
    }
}
