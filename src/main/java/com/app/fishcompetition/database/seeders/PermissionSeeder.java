package com.app.fishcompetition.database.seeders;

import com.app.fishcompetition.model.entity.AppPermission;
import com.app.fishcompetition.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionSeeder {

    private final PermissionRepository permissionRepository;

    public void seed(){
        if(permissionRepository.count() == 0){
            List<AppPermission> permissions = List.of(
                    AppPermission.builder().name("CAN_SHOW_PODIUM").build(),
                    AppPermission.builder().name("CAN_SHOW_COMPETITIONS").build(),
                    AppPermission.builder().name("CAN_ADD_COMPETITION").build(),
                    AppPermission.builder().name("CAN_EDIT_COMPETITION").build(),
                    AppPermission.builder().name("CAN_DELETE_COMPETITION").build(),
                    AppPermission.builder().name("CAN_SHOW_MEMBERS").build(),
                    AppPermission.builder().name("CAN_ADD_MEMBER").build(),
                    AppPermission.builder().name("CAN_EDIT_MEMBER").build(),
                    AppPermission.builder().name("CAN_DELETE_MEMBER").build(),
                    AppPermission.builder().name("CAN_SHOW_FISHES").build(),
                    AppPermission.builder().name("CAN_ADD_FISH").build(),
                    AppPermission.builder().name("CAN_EDIT_FISH").build(),
                    AppPermission.builder().name("CAN_DELETE_FISH").build(),
                    AppPermission.builder().name("CAN_SHOW_LEVELS").build(),
                    AppPermission.builder().name("CAN_ADD_LEVEL").build(),
                    AppPermission.builder().name("CAN_EDIT_LEVEL").build(),
                    AppPermission.builder().name("CAN_DELETE_LEVEL").build()
            );
            permissionRepository.saveAll(permissions);
        }
    }
}
