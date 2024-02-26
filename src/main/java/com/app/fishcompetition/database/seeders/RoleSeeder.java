package com.app.fishcompetition.database.seeders;

;
import com.app.fishcompetition.model.entity.AppPermission;
import com.app.fishcompetition.model.entity.AppRole;
import com.app.fishcompetition.repositories.RoleRepository;
import com.app.fishcompetition.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    public void seed(){
        if(roleRepository.count() == 0){
            List<AppRole> roles = List.of(
                    AppRole.builder().authority("ROLE_ADHERENT").build(),
                    AppRole.builder().authority("ROLE_JURY").build(),
                    AppRole.builder().authority("ROLE_MANAGER").build()
            );
            roleRepository.saveAll(roles);
            assignPermissionsToRoles();
        }
    }
    private void assignPermissionsToRoles() {

        roleService.AddPermissionToRole("ROLE_ADHERENT","CAN_SHOW_COMPETITIONS");
        roleService.AddPermissionToRole("ROLE_ADHERENT","CAN_SHOW_PODIUM");

        roleService.AddPermissionToRole("ROLE_JURY","CAN_SHOW_COMPETITIONS");
        roleService.AddPermissionToRole("ROLE_JURY","CAN_SHOW_PODIUM");
        roleService.AddPermissionToRole("ROLE_JURY","CAN_ADD_COMPETITION");
        roleService.AddPermissionToRole("ROLE_JURY","CAN_EDIT_COMPETITION");
        roleService.AddPermissionToRole("ROLE_JURY","CAN_DELETE_COMPETITION");

        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_SHOW_COMPETITIONS");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_SHOW_PODIUM");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_ADD_COMPETITION");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_EDIT_COMPETITION");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_DELETE_COMPETITION");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_SHOW_MEMBERS");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_EDIT_MEMBER");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_ADD_MEMBER");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_DELETE_MEMBER");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_SHOW_FISHES");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_ADD_FISH");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_EDIT_FISH");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_DELETE_FISH");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_SHOW_LEVELS");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_ADD_LEVEL");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_EDIT_LEVEL");
        roleService.AddPermissionToRole("ROLE_MANAGER","CAN_DELETE_LEVEL");
    }
}
