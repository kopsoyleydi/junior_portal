package com.example.junior_portal.model;

import com.example.junior_portal.enums.ConnectionType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "connections")
@Data
public class Connection {

    @Id
    @Column(name = "connection_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user1_id")
    @ManyToOne
    private User user1Id;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2Id;

    @Enumerated
    private ConnectionType connectionType;

}
