package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.AppPermission;
import com.app.fishcompetition.model.entity.AppRole;
import com.app.fishcompetition.repositories.PermissionRepository;
import com.app.fishcompetition.repositories.RoleRepository;
import com.app.fishcompetition.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    @Override
    @Transactional
    public void AddPermissionToRole(String roleName, String permissionName) {
        AppRole role = roleRepository.findByAuthority(roleName).orElseThrow(() -> new NoSuchElementException("No role found"));
        AppPermission permission = permissionRepository.findByName(permissionName).orElseThrow(() -> new NoSuchElementException("permission not found"));
        if(role.getPermissions() !=null){
            role.getPermissions().add(permission);
            permission.getRoles().add(role);
        }
    }
}
