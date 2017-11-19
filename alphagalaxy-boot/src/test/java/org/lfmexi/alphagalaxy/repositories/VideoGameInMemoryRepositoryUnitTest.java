package org.lfmexi.alphagalaxy.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.exceptions.DuplicatedIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { RepositoryTestConfig.class })
public class VideoGameInMemoryRepositoryUnitTest {

  @Autowired
  private List<VideoGame> videoGameSource;

  @Autowired
  private Repo<VideoGame> testRepo;

  @Test
  public void notNullTest() {
    assertNotNull(testRepo);
  }

  @Test
  public void insertVideoGamesTest() {
    testRepo.insert(new VideoGame("The Legend Of Zelda"));
    List<VideoGame> list = testRepo.find();
    assertNotNull(list);
    assertEquals("List size should be 4", 4, list.size());
  }

  @Test
  public void failToInsertDuplicatedIdTest() {
    boolean exceptionThrown = false;
    try {
      testRepo.insert(new VideoGame(1L, "Fakeo Kart"));
    } catch (RuntimeException e) {
      assertEquals("it should be a DuplicatedIdException", DuplicatedIdException.class, e.getClass());
      exceptionThrown = true;
    }

    assertTrue(exceptionThrown);
  }

  @Test
  public void findVideoGameByIdTest() {
    VideoGame v = testRepo.findById(1L);
    assertNotNull(v);
    assertEquals(new Long(1L), v.getId());
    assertEquals("It should be the first element", videoGameSource.get(0), v);
  }

  @Test
  public void filterByTitleVideoGamesTest() {
    String expectedTitle = "Mario";
    List<VideoGame> videoGames = testRepo.filter((videoGame) -> videoGame.getTitle().indexOf(expectedTitle) > -1);
    assertNotNull(videoGames);
    assertEquals("List size should be 2", 2, videoGames.size());
  }
}
