package com.zemoga.test.application.port.in;

import java.util.List;
import org.springframework.social.twitter.api.Tweet;

public interface TwitterUseCase {

  List<Tweet> getLatestTweets(String user);
}
