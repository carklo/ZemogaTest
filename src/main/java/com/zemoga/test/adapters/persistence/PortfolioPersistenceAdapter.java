package com.zemoga.test.adapters.persistence;

import com.zemoga.test.application.port.out.LoadPortfolioUserPort;
import com.zemoga.test.application.port.out.SavePortfolioUserPort;
import com.zemoga.test.common.PersistenceAdapter;
import com.zemoga.test.domain.model.PortfolioUser;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PortfolioPersistenceAdapter implements SavePortfolioUserPort, LoadPortfolioUserPort {

  private final PortfolioRepository portfolioRepository;

  @Override
  public PortfolioUser save (PortfolioUser portfolioUser) {
    return portfolioRepository.save(portfolioUser);
  }

  @Override
  public Optional<PortfolioUser> loadByEmail(String email) {
    return portfolioRepository.findPortfolioUserByEmail(email);
  }
}
