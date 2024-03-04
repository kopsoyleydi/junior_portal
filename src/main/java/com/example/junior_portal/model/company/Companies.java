package com.example.junior_portal.model.company;

import com.example.junior_portal.model.address.City;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "companies")
@Data
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UniqueElements
    private String companyName;

    private String address;

    @OneToOne
    private City city;

    private String link;

    private String socialMediaLink;

    private String email;
}
