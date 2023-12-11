package com.app.fishcompetition.database.seeders;

import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.repositories.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelSeeder {
    private final LevelRepository levelRepository;

    public void seed() {
        if(levelRepository.count() == 0) {
            List<Level> levels = List.of(
                    Level.builder().code("Freshwater Explorer").level(1).description("This level is for beginners who are starting their journey in freshwater fishing. They are learning about different freshwater habitats, such as rivers, lakes, and ponds, and the types of fish that can be found in these environments.").points(20).build(),
                    Level.builder().code("Coastal Adventurer").level(2).description("This level represents anglers with intermediate skills in coastal fishing. They have gained knowledge about fishing in coastal areas, including estuaries, bays, and nearshore locations, and have experience targeting various species found in these habitats.").points(40).build(),
                    Level.builder().code("Offshore Navigator").level(3).description("This level signifies experienced anglers who have ventured into offshore fishing. They have developed skills to locate and target fish species in deeper waters, away from the shorelines, and have an understanding of offshore fishing techniques and equipment.").points(60).build(),
                    Level.builder().code("Deep Sea Conqueror").level(4).description("This level is for expert anglers who have mastered deep-sea fishing. They possess extensive knowledge of offshore fishing grounds, including reefs, seamounts, and oceanic areas, and can successfully locate and catch a wide range of pelagic species.").points(80).build(),
                    Level.builder().code("Master Angler").level(5).description("This level represents master anglers who have achieved the highest level of proficiency in finding fish. They have an exceptional understanding of various fishing environments, including freshwater, coastal, and offshore, and can consistently locate and target fish across different habitats.").points(100).build()
            );
            levelRepository.saveAll(levels);
        }
    }
}
