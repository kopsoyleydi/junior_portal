package com.example.junior_portal.dtos.dto;

import com.example.junior_portal.enums.ConnectionType;
import com.example.junior_portal.model.User;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionDto {
    private Long id;

    @ManyToOne
    private UserDto user1Id;

    @ManyToOne
    private UserDto user2Id;

    @Enumerated
    private ConnectionType connectionType;
}
