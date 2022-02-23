package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table( name = "house")
public class Houses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String address;

    public Houses() {
    }

    public Houses(String address) {
        this.address=address;
    }
}
