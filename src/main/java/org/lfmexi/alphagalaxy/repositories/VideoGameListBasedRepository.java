package org.lfmexi.alphagalaxy.repositories;

import java.util.Observable;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.exceptions.DuplicatedRecordException;
import org.lfmexi.alphagalaxy.util.List;

public final class VideoGameListBasedRepository extends Observable implements VideoGameRepository {

  private Long autoIncrementID = 1L;

  private List<VideoGame> entityCollection;

  public VideoGameListBasedRepository(List<VideoGame> list) {
    this.entityCollection = list;
  }

  private void notifyChange() {
    setChanged();
    notifyObservers(this);
  }

  @Override
  public void insert(VideoGame videoGame) throws DuplicatedRecordException {
    if (videoGame.getId() == null) {
      videoGame.setId(autoIncrementID++);
    }

    if (this.findById(videoGame.getId()) != null) {
      throw new DuplicatedRecordException();
    }

    this.entityCollection.add(videoGame);
    notifyChange();
  }

  @Override
  public VideoGame findById(Object id) {
    VideoGame videoGameToFind = new VideoGame((Long) id, null, null);
    return this.entityCollection.find(videoGameToFind);
  }

  @Override
  public VideoGame[] filterByTitle(String title) {
    return this.entityCollection
        .subListWith(new VideoGame(null, title, null),
            (desiredVideoGame, currentVideoGame) -> currentVideoGame.getTitle().indexOf(desiredVideoGame.getTitle()))
        .toArray(VideoGame.class);
  }

  @Override
  public VideoGame[] filterByPlatform(String platform) {
    return this.entityCollection.subListWith(new VideoGame(null, null, platform),
        (desiredVideoGame, currentVideoGame) -> currentVideoGame.getPlatform().indexOf(desiredVideoGame.getPlatform()))
        .toArray(VideoGame.class);
  }

  @Override
  public int update(VideoGame videoGame) {
    VideoGame videoGameToUpdate = this.findById(videoGame.getId());

    if (videoGameToUpdate == null) {
      return 0;
    }

    videoGameToUpdate.merge(videoGame);
    notifyChange();
    return 1;
  }

  @Override
  public int delete(VideoGame element) {
    if (this.entityCollection.remove(element) == null) {
      return 0;
    }
    notifyChange();
    return 1;
  }

  @Override
  public VideoGame[] findAll() {
    return this.entityCollection.toArray(VideoGame.class);
  }

}
