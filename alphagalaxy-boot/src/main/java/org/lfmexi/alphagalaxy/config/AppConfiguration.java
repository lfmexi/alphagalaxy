package org.lfmexi.alphagalaxy.config;

import java.util.List;
import java.util.Observer;

import javax.swing.JList;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.VideoGameInMemoryRepoObservable;
import org.lfmexi.alphagalaxy.repositories.VideoGameRepoObservable;
import org.lfmexi.alphagalaxy.ui.observers.VideoGamesListObserver;
import org.lfmexi.alphagalaxy.ui.workers.SwingWorkerFactory;
import org.lfmexi.alphagalaxy.ui.workers.VideoGameListWorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ InMemoryRepoConfiguration.class })
public class AppConfiguration {

  @Bean
  public VideoGameRepoObservable videoGameRepoObservable() {
    VideoGameInMemoryRepoObservable observable = new VideoGameInMemoryRepoObservable();
    observable.addObserver(videoGamesListObserver());
    return observable;
  }

  @Bean
  public Observer videoGamesListObserver() {
    return new VideoGamesListObserver();
  };

  @Bean
  public SwingWorkerFactory<Void, List<VideoGame>> videoGameListWorkerFactory() {
    return new VideoGameListWorkerFactory(videoGamesJList());
  }

  @Bean
  public JList<VideoGame> videoGamesJList() {
    return new JList<VideoGame>();
  }

}
