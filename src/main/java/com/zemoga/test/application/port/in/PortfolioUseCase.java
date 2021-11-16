package com.zemoga.test.application.port.in;

import com.zemoga.test.domain.model.PortfolioUser;
import java.util.Optional;

public interface PortfolioUseCase {

  Optional<PortfolioUser> loadPortfolioUser(String email);

  PortfolioUser updatePortfolioUser(PortfolioUser portfolioUser);
}
