package org.lfmexi.alphagalaxy.ui.workers;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingWorker;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.Repo;

public class VideoGameListWorker extends SwingWorker<Void, List<VideoGame>> {
  private JList<VideoGame> videoGamesList;

  private Repo<VideoGame> videoGameRepo;

  public VideoGameListWorker(JList<VideoGame> list, Repo<VideoGame> videoGameRepo) {
    this.videoGamesList = list;
    this.videoGameRepo = videoGameRepo;
  }

  @Override
  protected Void doInBackground() {
    List<VideoGame> videoGames = videoGameRepo.find();
    System.out.println(videoGames);
    publish(videoGames);
    return null;
  }

  @Override
  protected void process(List<List<VideoGame>> chunks) {
    List<VideoGame> videoGames = chunks.get(chunks.size() - 1);

    DefaultListModel<VideoGame> model = new DefaultListModel<VideoGame>();
    for (VideoGame videoGame : videoGames) {
      model.addElement(videoGame);
    }

    videoGamesList.setModel(model);
  }

}
