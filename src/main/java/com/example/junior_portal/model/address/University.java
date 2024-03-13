package com.example.junior_portal.model.address;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "university")
@Data
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alpha_two_code;

    private String state_province;

    private String name;

    private String country;

}
