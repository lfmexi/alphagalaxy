package org.lfmexi.alphagalaxy.ui.observers;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.ui.workers.SwingWorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VideoGamesListObserver implements Observer {

  private SwingWorkerFactory<Void, List<VideoGame>> workerFactory;

  @Override
  public void update(Observable o, Object arg) {
    workerFactory.createSwingWorker().execute();
  }

  @Autowired
  public void setWorkerFactory(SwingWorkerFactory<Void, List<VideoGame>> workerFactory) {
    this.workerFactory = workerFactory;
  }
}
