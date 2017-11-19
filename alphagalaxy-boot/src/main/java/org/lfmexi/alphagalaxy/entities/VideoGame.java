package org.lfmexi.alphagalaxy.entities;

public class VideoGame {

  private Long id;
  private String title;

  public VideoGame(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  public VideoGame(String title) {
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long newId) {
    this.id = newId;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return String.format("Title: %s", title);
  }

}
