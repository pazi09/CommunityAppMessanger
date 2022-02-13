package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table( name = "tenant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "tenantName")
        })
public class Tenants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tenantName;

    @OneToOne
    @JoinColumn(name = "user_id" ,unique = true)
    public User user;

    public Tenants(){};
}
