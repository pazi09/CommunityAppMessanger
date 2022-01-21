package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table( name = "flat",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "flatNumber")
        })
public class Flats {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private Long flatNumber;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(	name = "flats_tenants",
                joinColumns = @JoinColumn(name = "flat_id"),
                inverseJoinColumns = @JoinColumn(name = "tenant_id"))
        private Set<Tenants> tenants=new HashSet<>();

        public Flats(){
        }
}
