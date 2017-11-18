package org.lfmexi.alphagalaxy.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.lfmexi.alphagalaxy.config.Context;
import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.VideoGameRepository;

public class VideoGamesView extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  final private Context context;
  private JScrollPane scrollPane;
  private JList<VideoGame> list;

  private Observer listObserver;

  public VideoGamesView(Context context) {
    this.context = context;
    initializeUI();
    setUpObservers();
    loadVideoGamesList();

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        context.videoGameObservable().deleteObserver(listObserver);
      }
    });
  }

  private void setUpObservers() {
    listObserver = (observable, object) -> loadVideoGamesList();
    this.context.videoGameObservable().addObserver(listObserver);
  }

  private void initializeUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JButton btnAddVideoGame = new JButton("Add Video Game");
    btnAddVideoGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        EventQueue.invokeLater(() -> {
          VideoGameForm view = new VideoGameForm(context);
          view.setVisible(true);
        });
      }
    });

    list = new JList<VideoGame>();

    scrollPane = new JScrollPane(list);
    contentPane.add(scrollPane, BorderLayout.CENTER);

    contentPane.add(btnAddVideoGame, BorderLayout.WEST);
  }

  private void loadVideoGamesList() {
    VideoGameListWorker listWorker = new VideoGameListWorker(this.context.videoGameRepository());
    listWorker.execute();
  }

  private class VideoGameListWorker extends SwingWorker<Void, VideoGame[]> {
    private VideoGameRepository repository;

    VideoGameListWorker(VideoGameRepository repository) {
      this.repository = repository;
    }

    @Override
    protected Void doInBackground() throws Exception {
      // TODO Auto-generated method stub
      System.out.println(repository.findAll());
      publish(repository.findAll());
      return null;
    }

    @Override
    protected void process(List<VideoGame[]> chunks) {
      // TODO Auto-generated method stub
      VideoGame[] videoGames = chunks.get(chunks.size() - 1);

      DefaultListModel<VideoGame> model = new DefaultListModel<VideoGame>();
      for (VideoGame videoGame : videoGames) {
        model.addElement(videoGame);
      }

      list.setModel(model);
    }

  }
}
