package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table( name = "flat")
public class Flat {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private Long flatNumber;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(	name = "flats_tenants",
                joinColumns = @JoinColumn(name = "flat_id"),
                inverseJoinColumns = @JoinColumn(name = "tenant_id"))
        private Set<Tenant> tenants=new HashSet<>();

        @ManyToOne(fetch = FetchType.EAGER, optional = false,cascade = CascadeType.ALL)
        @JoinColumn(name = "house_id")
        private House house;

        public Flat(){
        }

}
