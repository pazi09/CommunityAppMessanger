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
public class Tenant {
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



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Tenant() {
    }

    public Tenant(String tenantName, String tenantSecondName, String tenantLastName) {
        this.tenantName=tenantName;
        this.tenantSecondName=tenantSecondName;
        this.tenantLastName=tenantLastName;

    }


}
