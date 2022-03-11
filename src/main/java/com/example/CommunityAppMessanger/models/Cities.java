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
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @OneToMany
    @Column(name="houses" , unique = false, nullable = true)
    Set<Houses> houses;

    public Cities() {
    }


    public Cities(String city,HashSet<Houses> houses){
        this.city=city;
        this.houses=houses;
    }
}
