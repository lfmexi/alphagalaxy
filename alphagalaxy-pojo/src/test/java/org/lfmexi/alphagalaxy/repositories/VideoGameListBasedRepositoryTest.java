package org.lfmexi.alphagalaxy.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.exceptions.DuplicatedRecordException;
import org.lfmexi.alphagalaxy.util.AlphaLinkedList;
import org.lfmexi.alphagalaxy.util.List;

public class VideoGameListBasedRepositoryTest {

  private List<VideoGame> innerList;

  @Before
  public void initializeInnerList() {
    innerList = new AlphaLinkedList<>();
  }

  @Test
  public void createVideoGameRepositoryTest() {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    assertNotNull("should not be null", repo);
  }

  @Test
  public void insertNewVideoGames() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(null, "new", "other"));
    repo.insert(new VideoGame(null, "second", "another"));

    assertEquals(2, innerList.size());
  }

  @Test
  public void insertASingleVideogame() {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    try {
      repo.insert(new VideoGame(1L, "test", "test"));
      repo.insert(new VideoGame(1L, "test", "test"));
    } catch (DuplicatedRecordException e) {
    }

    assertEquals(1, innerList.size());
  }

  @Test
  public void findUnexistentByIDTest() {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    VideoGame videoGame = repo.findById(new Long(1));

    assertNull(videoGame);
  }

  @Test
  public void findExistentByIDTest() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(1L, "test", "test"));

    VideoGame videoGame = repo.findById(new Long(1));

    assertNotNull(videoGame);
  }

  @Test
  public void findNoneWithTitleFilter() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(null, "new", "other"));
    repo.insert(new VideoGame(null, "second", "another"));
    repo.insert(new VideoGame(null, "third", "and Another"));

    VideoGame[] videoGamesFound = repo.filterByTitle("fourth");

    assertEquals(0, videoGamesFound.length);
  }

  @Test
  public void findTwoWithTitleFilter() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(null, "new", "other"));
    repo.insert(new VideoGame(null, "second", "another"));
    repo.insert(new VideoGame(null, "third", "and Another"));

    VideoGame[] videoGamesFound = repo.filterByTitle("e");

    assertEquals(2, videoGamesFound.length);
  }

  @Test
  public void findNoneWithPlatformFilter() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(null, "new", "other"));
    repo.insert(new VideoGame(null, "second", "another"));
    repo.insert(new VideoGame(null, "third", "and Another"));

    VideoGame[] videoGamesFound = repo.filterByPlatform("neither");

    assertEquals(0, videoGamesFound.length);
  }

  @Test
  public void findTwoWithPlatformFilter() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(null, "new", "other"));
    repo.insert(new VideoGame(null, "second", "another"));
    repo.insert(new VideoGame(null, "third", "and Another"));

    VideoGame[] videoGamesFound = repo.filterByPlatform("an");

    assertEquals(2, videoGamesFound.length);
  }

  @Test
  public void notUpdateAnything() {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    assertEquals(0, repo.update(new VideoGame(1L, "new", "other")));
  }

  @Test
  public void updateFields() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(null, "new", "other"));

    assertEquals(1, repo.update(new VideoGame(1L, "anotherTitle", "myPlatform")));

    VideoGame updatedVideoGame = repo.findById(1L);

    assertEquals("anotherTitle", updatedVideoGame.getTitle());
    assertEquals("myPlatform", updatedVideoGame.getPlatform());
  }

  @Test
  public void notRemovingElements() {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    assertEquals(0, repo.delete(new VideoGame(1L, "new", "other")));
  }

  @Test
  public void removingAnElement() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(null, "new", "other"));

    assertEquals(1, repo.delete(new VideoGame(1L, null, null)));

    VideoGame deletedVideoGame = repo.findById(1L);

    assertNull(deletedVideoGame);
  }

  @Test
  public void listAllTheInsertedVideoGames() throws DuplicatedRecordException {
    VideoGameRepository repo = new VideoGameListBasedRepository(innerList);

    repo.insert(new VideoGame(null, "new", "other"));
    repo.insert(new VideoGame(null, "second", "another"));
    repo.insert(new VideoGame(null, "third", "and Another"));

    VideoGame[] videoGamesFound = repo.findAll();

    assertEquals(3, videoGamesFound.length);
  }
}
