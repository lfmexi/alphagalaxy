package org.lfmexi.alphagalaxy.config;

import java.util.Observable;

import org.lfmexi.alphagalaxy.repositories.VideoGameRepository;

public interface Context {
  public VideoGameRepository videoGameRepository();

  public Observable videoGameObservable();
}
