package com.zemoga.test.application.port.out;

import com.zemoga.test.domain.model.PortfolioUser;

public interface SavePortfolioUserPort {

  PortfolioUser save(PortfolioUser portfolioUser);
}
