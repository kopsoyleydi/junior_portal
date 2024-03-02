package com.example.junior_portal.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "internships")
@Data
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "linktopicture")
    private String linktopicture;
}
