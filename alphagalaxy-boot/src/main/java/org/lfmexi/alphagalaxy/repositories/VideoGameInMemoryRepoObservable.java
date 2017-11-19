package org.lfmexi.alphagalaxy.repositories;

import org.lfmexi.alphagalaxy.entities.VideoGame;

public class VideoGameInMemoryRepoObservable extends VideoGameRepoObservable {

  @Override
  public void notifyChange(VideoGame changed) {
    this.setChanged();
    this.notifyObservers(changed);
  }

}
