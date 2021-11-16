package com.zemoga.test.adapters.persistence;

import com.zemoga.test.domain.model.PortfolioUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<PortfolioUser, Long> {

  Optional<PortfolioUser> findPortfolioUserByName(String name);
  Optional<PortfolioUser> findPortfolioUserByEmail(String email);
  Optional<PortfolioUser> findPortfolioUserById(Long id);
}
