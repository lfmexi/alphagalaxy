package org.lfmexi.alphagalaxy.repositories;

import java.util.Observable;

import org.lfmexi.alphagalaxy.entities.VideoGame;

public abstract class VideoGameRepoObservable extends Observable {
  public abstract void notifyChange(VideoGame changed);
}
