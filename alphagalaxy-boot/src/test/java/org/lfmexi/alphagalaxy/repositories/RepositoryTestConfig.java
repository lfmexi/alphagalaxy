package org.lfmexi.alphagalaxy.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryTestConfig {

  @Bean
  public VideoGameRepoObservable videoGameRepoObservable() {
    VideoGameInMemoryRepoObservable observable = new VideoGameInMemoryRepoObservable();
    return observable;
  }
}
