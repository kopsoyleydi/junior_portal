package com.example.junior_portal.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "internships")
@Data
public class Internship {

    @Id
    @Column(name = "internship_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;
}
