package com.app.fishcompetition.database.seeders;


import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.repositories.FishRepository;
import com.app.fishcompetition.repositories.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FishSeeder {
    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;
    public void seed(){
        if(fishRepository.count() == 0){
            List<Fish> fishes = List.of(
                    Fish.builder().name("Bass").averageWeight(2.5).level(Level.builder().level(1).build()).build(),
                    Fish.builder().name("Catfish").level(Level.builder().level(2).build()).averageWeight(1.5).build(),
                    Fish.builder().name("Crappie").level(Level.builder().level(3).build()).averageWeight(0.5).build(),
                    Fish.builder().name("Muskellunge").level(Level.builder().level(4).build()).averageWeight(3.5).build(),
                    Fish.builder().name("Northern pike").level(Level.builder().level(5).build()).averageWeight(2.5).build(),
                    Fish.builder().name("Perch").level(Level.builder().level(2).build()).averageWeight(0.5).build(),
                    Fish.builder().name("Salmon").level(Level.builder().level(5).build()).averageWeight(1.5).build(),
                    Fish.builder().name("Trout").level(Level.builder().level(4).build()).averageWeight(1.5).build(),
                    Fish.builder().name("Walleye").level(Level.builder().level(3).build()).averageWeight(1.5).build(),
                    Fish.builder().name("Sardine").level(Level.builder().level(1).build()).averageWeight(2.5).build()
            );
            for (Fish fish : fishes) {
                Level level = levelRepository.findByLevel(fish.getLevel().getLevel());
                if (level != null) {
                    fish.setLevel(level);
                }
            }
            fishRepository.saveAll(fishes);
        }
    }
}
