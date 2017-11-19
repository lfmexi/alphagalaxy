package org.lfmexi.alphagalaxy.ui.workers;

import java.util.List;

import javax.swing.JList;
import javax.swing.SwingWorker;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;

public class VideoGameListWorkerFactory implements SwingWorkerFactory<Void, List<VideoGame>> {

  private Repo<VideoGame> videoGameRepo;

  private JList<VideoGame> videoGameJList;

  public VideoGameListWorkerFactory(JList<VideoGame> jlist) {
    videoGameJList = jlist;
  }

  @Autowired
  public void setVideoGameRepo(Repo<VideoGame> videoGameRepo) {
    this.videoGameRepo = videoGameRepo;
  }

  @Override
  public SwingWorker<Void, List<VideoGame>> createSwingWorker() {
    return new VideoGameListWorker(videoGameJList, videoGameRepo);
  }

}
