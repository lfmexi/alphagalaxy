package org.lfmexi.alphagalaxy.controllers;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class VideoGameController implements AbstractController<VideoGame> {

  @Autowired
  private Repo<VideoGame> videoGameRepository;

  public void post(VideoGame videoGame) {
    videoGameRepository.insert(videoGame);
  }
}
