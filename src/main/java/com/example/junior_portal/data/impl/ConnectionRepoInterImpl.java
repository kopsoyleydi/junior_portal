package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.ConnectionRepoInter;
import com.example.junior_portal.data.repository.ConnectionRepository;
import com.example.junior_portal.model.Connection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class ConnectionRepoInterImpl implements ConnectionRepoInter {

    private final ConnectionRepository connectionRepository;

    @Override
    public Connection createConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    @Override
    public void deleteConnection(Long id) {
        connectionRepository.deleteById(id);
    }
}
