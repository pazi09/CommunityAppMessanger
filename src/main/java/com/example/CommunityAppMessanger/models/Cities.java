package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @ManyToMany
    @JoinTable(	name = "cities_houses",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "house_id"))
    private Set<Houses> houses=new HashSet<>();

    public Cities() {
    }
}
