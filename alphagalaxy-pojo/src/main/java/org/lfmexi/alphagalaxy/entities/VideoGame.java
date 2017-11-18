package org.lfmexi.alphagalaxy.entities;

public class VideoGame {
  private Long id;
  private String title;
  private String platform;

  public VideoGame() {

  }

  private static boolean isStringEmpty(String toTest) {
    return toTest == null || toTest.equals("");
  }

  public void merge(VideoGame videoGame) {
    if (!this.title.equals(videoGame.title) && !isStringEmpty(videoGame.title)) {
      this.title = videoGame.title;
    }

    if (!this.platform.equals(videoGame.platform) && !isStringEmpty(videoGame.platform)) {
      this.platform = videoGame.platform;
    }

  }

  public VideoGame(Long id, String title, String platform) {
    this.id = id;
    this.title = title;
    this.platform = platform;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return String.format("[id=%s, title=%s, platform=%s]", id, title, platform);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    VideoGame other = (VideoGame) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
