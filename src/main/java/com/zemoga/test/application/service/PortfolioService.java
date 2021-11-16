package com.zemoga.test.application.service;

import com.zemoga.test.application.port.in.PortfolioUseCase;
import com.zemoga.test.application.port.in.TwitterUseCase;
import com.zemoga.test.application.port.out.LoadPortfolioUserPort;
import com.zemoga.test.common.UseCase;
import com.zemoga.test.domain.model.PortfolioUser;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

@UseCase
@RequiredArgsConstructor
public class PortfolioService implements PortfolioUseCase, TwitterUseCase {

  private final LoadPortfolioUserPort loadPortfolioUserPort;
  private final Twitter twitter;

  @Override
  @Cacheable(value = "portfolios", key = "email")
  public Optional<PortfolioUser> loadPortfolioUser(String email) {
    return loadPortfolioUserPort.loadByEmail(email);
  }

  @Override
  @CachePut(value = "portfolios", key = "#portfolioUser.email")
  public PortfolioUser updatePortfolioUser(PortfolioUser portfolioUser) {
    return loadPortfolioUserPort.save(portfolioUser);
  }

  @Override
  @Cacheable(value = "tweets", key = "user")
  public List<Tweet> getLatestTweets(String user) {
    return twitter.timelineOperations().getUserTimeline(user, 5);
  }
}
