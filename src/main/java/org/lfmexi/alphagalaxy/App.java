package org.lfmexi.alphagalaxy;

import java.awt.EventQueue;
import java.util.Observable;

import org.lfmexi.alphagalaxy.config.Context;
import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.exceptions.DuplicatedRecordException;
import org.lfmexi.alphagalaxy.repositories.VideoGameListBasedRepository;
import org.lfmexi.alphagalaxy.repositories.VideoGameRepository;
import org.lfmexi.alphagalaxy.ui.VideoGamesView;
import org.lfmexi.alphagalaxy.util.AlphaLinkedList;
import org.lfmexi.alphagalaxy.util.List;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) throws DuplicatedRecordException {
    System.out.println("Hello World!");

    final List<VideoGame> list = new AlphaLinkedList<VideoGame>();

    final VideoGameRepository repository = new VideoGameListBasedRepository(list);

    repository.insert(new VideoGame(null, "Mortal Kombat", "Nintendo"));
    repository.insert(new VideoGame(null, "Mortal Kombat 2", "Nintendo"));
    repository.insert(new VideoGame(null, "Mortal Kombat 44", "Nintendo"));

    final Context ctx = new Context() {

      @Override
      public VideoGameRepository videoGameRepository() {
        // TODO Auto-generated method stub
        return repository;
      }

      @Override
      public Observable videoGameObservable() {
        return (VideoGameListBasedRepository) repository;
      }

    };

    EventQueue.invokeLater(() -> {
      VideoGamesView view = new VideoGamesView(ctx);
      view.setVisible(true);
    });
  }
}
