package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.PermissionRepoInter;
import com.example.junior_portal.data.repository.PermissionRepository;
import com.example.junior_portal.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionRepoInterImpl implements PermissionRepoInter {

    private final PermissionRepository permissionRepository;
    public Permission getPermissionById(Long id){
        return permissionRepository.findAllById(id);
    }
}
