package com.example.junior_portal.model.address;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "city")
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;
}
