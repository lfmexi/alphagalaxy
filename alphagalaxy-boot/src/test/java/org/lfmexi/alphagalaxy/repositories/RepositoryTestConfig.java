package org.lfmexi.alphagalaxy.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryTestConfig {

  @Bean
  public List<VideoGame> testSource() {
    VideoGame[] games = { new VideoGame(1L, "Mario Kart"), new VideoGame(2L, "Mario Odyssey"),
        new VideoGame(3L, "Mortal Kombat") };
    return new ArrayList<VideoGame>(Arrays.asList(games));
  }

  @Bean
  public Repo<VideoGame> videoGames() {
    VideoGameInMemoryRepository vrepo = new VideoGameInMemoryRepository(testSource());
    vrepo.setCurrentId(4L);
    return vrepo;
  }
}
