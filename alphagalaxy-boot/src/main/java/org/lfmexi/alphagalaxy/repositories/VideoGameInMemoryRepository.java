package org.lfmexi.alphagalaxy.repositories;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.exceptions.DuplicatedIdException;

public class VideoGameInMemoryRepository implements Repo<VideoGame> {

  private Long currentId = 1L;

  private List<VideoGame> videoGames;

  private Observable observable;

  public void setObservable(Observable observable) {
    this.observable = observable;
  }

  public VideoGameInMemoryRepository(List<VideoGame> videoGamesSource) {
    videoGames = videoGamesSource;
  }

  public void setCurrentId(Long currentId) {
    this.currentId = currentId;
  }

  @Override
  public List<VideoGame> find() {
    return videoGames.subList(0, videoGames.size());
  }

  @Override
  public void insert(VideoGame videoGame) {
    if (videoGame.getId() == null) {
      videoGame.setId(currentId++);
    }

    VideoGame found = null;

    try {
      found = this.findById(videoGame.getId());
    } catch (NoSuchElementException e) {
    }

    if (found != null) {
      throw new DuplicatedIdException("Id " + found.getId() + " already exists in the repository");
    }

    videoGames.add(videoGame);
    notifyObservers();
  }

  @Override
  public VideoGame findById(Long l) {
    return videoGames.stream().filter((v) -> l == v.getId()).findFirst().get();
  }

  @Override
  public List<VideoGame> filter(Predicate<? super VideoGame> predicate) {
    return videoGames.stream().filter(predicate).collect(Collectors.toList());
  }

  private void notifyObservers() {
    if (observable != null) {
      observable.notifyAll();
    }
  }
}
