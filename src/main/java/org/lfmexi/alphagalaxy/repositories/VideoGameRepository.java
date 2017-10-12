package org.lfmexi.alphagalaxy.repositories;

import org.lfmexi.alphagalaxy.entities.VideoGame;

public interface VideoGameRepository extends Repository<VideoGame> {
  public VideoGame[] filterByTitle(String title);

  public VideoGame[] filterByPlatform(String platform);
}
