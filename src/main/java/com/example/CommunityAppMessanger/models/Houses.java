package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table( name = "house",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "address")
        })
public class Houses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String address;

    @NotBlank
    @Size(max = 1000)
    private Long cityId;

    @ManyToMany
    @JoinTable(	name = "houses_flats",
            joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "flat_id"))
    private Set<Flats> flats=new HashSet<>();

    public Houses() {
    }
}
