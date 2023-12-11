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
    @PostConstruct
    public void init() {
        levelSeeder.seed();
        fishSeeder.seed();
    }
}
