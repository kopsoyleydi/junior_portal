package com.example.junior_portal.model.address;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "university")
@Data
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String webSite;

    @OneToOne
    private City city;

    private String street;

    private String buildingNumber;

}
