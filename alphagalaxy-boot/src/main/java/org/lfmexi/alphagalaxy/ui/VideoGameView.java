package org.lfmexi.alphagalaxy.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.ui.workers.SwingWorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoGameView extends JFrame {

  private static final long serialVersionUID = 1L;

  @Autowired
  private JFrame videoGameForm;

  @Autowired
  private JList<VideoGame> videoGamesList;

  @Autowired
  private SwingWorkerFactory<Void, List<VideoGame>> listWorkerFactory;

  @PostConstruct
  public void init() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(new Dimension(600, 400));
    setState(JFrame.NORMAL);
    addComponents();
    listWorkerFactory.createSwingWorker().execute();
    setVisible(true);
  }

  private void addComponents() {
    JPanel contentPane = createContentPane();
    contentPane.add(createScrollListView(), BorderLayout.CENTER);
    contentPane.add(createAddElementButton(), BorderLayout.BEFORE_LINE_BEGINS);
    setContentPane(contentPane);
  }

  private JPanel createContentPane() {
    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    return contentPane;
  }

  private JScrollPane createScrollListView() {
    return new JScrollPane(videoGamesList);
  }

  private JButton createAddElementButton() {
    JButton btnAddVideoGame = new JButton("Add Video Game");
    btnAddVideoGame.addActionListener((e) -> handleOnClickButton());
    return btnAddVideoGame;
  }

  private void handleOnClickButton() {
    EventQueue.invokeLater(() -> videoGameForm.setVisible(true));
  }
}
