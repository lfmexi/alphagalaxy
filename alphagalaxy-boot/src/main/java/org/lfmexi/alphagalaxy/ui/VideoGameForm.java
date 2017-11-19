package org.lfmexi.alphagalaxy.ui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.lfmexi.alphagalaxy.controllers.AbstractController;
import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoGameForm extends JFrame {

  private static final long serialVersionUID = 1L;

  private JTextField titleTextField;

  @Autowired
  private AbstractController<VideoGame> controller;

  public VideoGameForm() {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setSize(new Dimension(400, 200));
    setState(JFrame.NORMAL);
    addComponents();
  }

  private void addComponents() {
    JPanel contentPane = createContentPane();
    contentPane.add(getTitleLabel());
    contentPane.add(getTitleTextField());
    contentPane.add(getSubmmitButton());
    setContentPane(contentPane);
  }

  private JLabel getTitleLabel() {
    JLabel lblName = new JLabel("Title");
    lblName.setBounds(40, 66, 60, 15);
    return lblName;
  }

  private JTextField getTitleTextField() {
    titleTextField = new JTextField();
    titleTextField.setBounds(162, 64, 114, 19);
    titleTextField.setColumns(10);
    return titleTextField;
  }

  private JPanel createContentPane() {
    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    return contentPane;
  }

  private JButton getSubmmitButton() {
    JButton btnSubmmit = new JButton("Submmit");
    btnSubmmit.setBounds(162, 220, 114, 25);
    btnSubmmit.addActionListener((e) -> createVideoGame());
    return btnSubmmit;
  }

  private void createVideoGame() {
    String title = titleTextField.getText();
    if (title != null && !title.equals("")) {
      controller.post(new VideoGame(title));
      titleTextField.setText("");
    }
  }
}
