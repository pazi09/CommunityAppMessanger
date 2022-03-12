package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table( name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    public House() {
    }

    public House(String address) {
        this.address=address;
    }
}
