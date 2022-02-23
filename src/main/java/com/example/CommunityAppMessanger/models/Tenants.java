package com.example.CommunityAppMessanger.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table( name = "tenant")
public class Tenants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column
    private String tenantName;

    @Column
    private String tenantSecondName;

    @Column
    private String tenantLastName;

    @Column(name="user_id",unique = true)
    private Long user_id;

    public Tenants() {
    }

    public Tenants(String tenantName, String tenantSecondName, String tenantLastName,Long user_id) {
        this.tenantName=tenantName;
        this.tenantSecondName=tenantSecondName;
        this.tenantLastName=tenantLastName;
        this.user_id=user_id;
    }


}
