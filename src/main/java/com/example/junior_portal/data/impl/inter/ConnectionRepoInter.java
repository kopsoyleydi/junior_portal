package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.Connection;

public interface ConnectionRepoInter {

    Connection createConnection(Connection connection);

    void deleteConnection(Long id);
}
