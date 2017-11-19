package org.lfmexi.alphagalaxy.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Observable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lfmexi.alphagalaxy.config.InMemoryRepoConfiguration;
import org.lfmexi.alphagalaxy.entities.VideoGame;
import org.lfmexi.alphagalaxy.repositories.exceptions.DuplicatedIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { RepositoryTestConfig.class, InMemoryRepoConfiguration.class })
public class VideoGameInMemoryRepositoryUnitTest {

  private static boolean initDone = false;

  @Autowired
  private Repo<VideoGame> testRepo;

  @Autowired
  private VideoGameRepoObservable observable;

  @Before
  public void initTests() {
    if (initDone) {
      return;
    }
    testRepo.insert(new VideoGame("Mario Kart"));
    testRepo.insert(new VideoGame("Mario Odyssey"));
    testRepo.insert(new VideoGame("Mortal Kombat"));
    initDone = true;
  }

  @After
  public void afterTests() {
    observable.deleteObservers();
  }

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
  public void notifyToAObserverTest() {
    observable.addObserver((Observable o, Object subject) -> {
      assertEquals("This should be called", subject.getClass(), VideoGame.class);
    });

    testRepo.insert(new VideoGame("A new game"));
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
  }

  @Test
  public void filterByTitleVideoGamesTest() {
    String expectedTitle = "Mario";
    List<VideoGame> videoGames = testRepo.filter((videoGame) -> videoGame.getTitle().indexOf(expectedTitle) > -1);
    assertNotNull(videoGames);
    assertEquals("List size should be 2", 2, videoGames.size());
  }
}
