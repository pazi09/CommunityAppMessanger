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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "tenants_users",
            joinColumns = @JoinColumn(name = "tenant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users=new HashSet<>();

    public Tenants(){};
}
