package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table( name = "flat")
public class Flats {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "house_id", insertable=false, updatable = false)
        private Long house_id;

        @Column
        private Long flatNumber;

        @Column
        private Float square;

        @Column
        private Long rooms;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(	name = "flats_tenants",
                joinColumns = @JoinColumn(name = "flat_id"),
                inverseJoinColumns = @JoinColumn(name = "tenant_id"))
        private Set<Tenants> flat=new HashSet<>();

        @ManyToOne(fetch = FetchType.EAGER, optional = false,cascade = CascadeType.ALL)
        @JoinColumn(name = "house_id")
        private Houses house;

        public Flats(){
        }

        public Flats( Long flatNumber, Long rooms, Float square) {
                this.flatNumber=flatNumber;
                this.square=square;
                this.rooms=rooms;
        }
}
