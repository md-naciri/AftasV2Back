package com.app.fishcompetition.database.seeders;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DatabaseSeeder {
    private final LevelSeeder levelSeeder;
    private final FishSeeder fishSeeder;
    private final RoleSeeder roleSeeder;
    private final MemberSeeder memberSeeder;
    private final PermissionSeeder permissionSeeder;
    @PostConstruct
    public void init() {
        levelSeeder.seed();
        fishSeeder.seed();
        permissionSeeder.seed();
        roleSeeder.seed();
        memberSeeder.seed();
    }
}
