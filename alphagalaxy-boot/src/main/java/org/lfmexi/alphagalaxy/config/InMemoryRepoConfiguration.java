package org.lfmexi.alphagalaxy.config;

import java.util.ArrayList;
import java.util.List;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.Repo;
import org.lfmexi.alphagalaxy.repositories.VideoGameInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryRepoConfiguration {

  @Bean
  public List<VideoGame> videoGamesList() {
    return new ArrayList<VideoGame>();
  }

  @Bean
  public Repo<VideoGame> videoGameRepo() {
    VideoGameInMemoryRepository vr = new VideoGameInMemoryRepository(videoGamesList());
    return vr;
  }

}
