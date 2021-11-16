package com.zemoga.test.application.port.out;

import com.zemoga.test.domain.model.PortfolioUser;
import java.util.Optional;

public interface LoadPortfolioUserPort {

  Optional<PortfolioUser> loadByEmail(String email);

  PortfolioUser save(PortfolioUser portfolioUser);
}
