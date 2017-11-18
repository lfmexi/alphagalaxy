package org.lfmexi.alphagalaxy.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.lfmexi.alphagalaxy.config.Context;
import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.exceptions.DuplicatedRecordException;
import org.lfmexi.alphagalaxy.repositories.VideoGameRepository;

public class VideoGameForm extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField titleTextField;
  private JTextField platformTextField;

  private Context context;

  public VideoGameForm(Context context) {
    this.context = context;
    initializeUI();
  }

  private void initializeUI() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblName = new JLabel("Title");
    lblName.setBounds(40, 66, 60, 15);
    contentPane.add(lblName);

    titleTextField = new JTextField();
    titleTextField.setBounds(162, 64, 114, 19);
    contentPane.add(titleTextField);
    titleTextField.setColumns(10);

    JLabel lblPlatform = new JLabel("Platform");
    lblPlatform.setBounds(40, 144, 60, 15);
    contentPane.add(lblPlatform);

    platformTextField = new JTextField();
    platformTextField.setBounds(162, 142, 114, 19);
    contentPane.add(platformTextField);
    platformTextField.setColumns(10);

    JButton btnSubmmit = new JButton("Submmit");
    btnSubmmit.setBounds(162, 220, 114, 25);
    btnSubmmit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        createVideoGame();
      }
    });
    contentPane.add(btnSubmmit);
  }

  private void createVideoGame() {
    String title = this.titleTextField.getText();
    String platform = this.platformTextField.getText();

    if (title.equals("") || platform.equals("")) {
      JOptionPane.showMessageDialog(this, "Both fields are required");
      return;
    }

    VideoGameRepository repository = context.videoGameRepository();

    try {
      repository.insert(new VideoGame(null, title, platform));
    } catch (DuplicatedRecordException e) {
      JOptionPane.showMessageDialog(this, "This videogame already exists");
      e.printStackTrace();
    }
    cleanForm();
  }

  private void cleanForm() {
    this.titleTextField.setText("");
    this.platformTextField.setText("");
  }
}
