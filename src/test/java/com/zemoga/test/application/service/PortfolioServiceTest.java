package com.zemoga.test.application.service;

import com.zemoga.test.application.port.out.LoadPortfolioUserPort;
import com.zemoga.test.domain.model.PortfolioUser;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PortfolioServiceTest {

  private final LoadPortfolioUserPort loadPortfolioUserPort = Mockito.mock(LoadPortfolioUserPort.class);
  private final Twitter twitter = Mockito.mock(Twitter.class);
  private final PortfolioService serviceUnderTest = new PortfolioService(loadPortfolioUserPort, twitter);

  @Test
  void loadPortfolioUser() {
    when(loadPortfolioUserPort.loadByEmail(anyString())).thenReturn(Optional.of(PortfolioUser.builder().build()));
    Optional<PortfolioUser> optionalPortfolioUser = serviceUnderTest.loadPortfolioUser("santidils7@gmail.com");
    Assertions.assertNotNull(optionalPortfolioUser.get());
  }

  @Test
  void updatePortfolioUser() {
    PortfolioUser portfolioUser = PortfolioUser.builder().build();
    when(loadPortfolioUserPort.save(any())).thenReturn(portfolioUser);
    PortfolioUser portfolioUserStored = serviceUnderTest.updatePortfolioUser(portfolioUser);
    Assertions.assertNotNull(portfolioUserStored);
  }

  @Test
  void getLatestTweets() {
    TimelineOperations timelineOperations = Mockito.mock(TimelineOperations.class);
    when(twitter.timelineOperations()).thenReturn(timelineOperations);
    when(timelineOperations.getUserTimeline(any(String.class), any(Integer.class))).thenReturn(Collections.emptyList());
    List<Tweet> testTweets = serviceUnderTest.getLatestTweets("test");
    Assertions.assertTrue(testTweets.isEmpty());
  }
}
