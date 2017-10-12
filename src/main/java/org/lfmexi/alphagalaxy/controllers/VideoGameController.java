package org.lfmexi.alphagalaxy.controllers;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.VideoGameRepository;

public class VideoGameController {

  private VideoGameRepository repository;

  public VideoGameController(VideoGameRepository repository) {
    this.repository = repository;
  }

  public VideoGame[] getAllTheVideoGames() {
    return this.repository.findAll();
  }

  public VideoGame[] filterVideoGamesByTitle(String title) {
    return this.repository.filterByTitle(title);
  }
}
