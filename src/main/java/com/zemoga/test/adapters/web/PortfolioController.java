package com.zemoga.test.adapters.web;

import com.zemoga.test.application.port.in.PortfolioUseCase;
import com.zemoga.test.application.port.in.TwitterUseCase;
import com.zemoga.test.domain.model.PortfolioUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PortfolioController {

  private final PortfolioUseCase portfolioUseCase;
  private final TwitterUseCase twitterUseCase;

  @GetMapping(path = "/")
  public String index(@RequestParam String email, Model model) {
    PortfolioUser portfolioUser = portfolioUseCase.loadPortfolioUser(email)
        .orElseThrow(() -> new ResourceNotFoundException("", "User not found with email " + email));;
    model.addAttribute("portfolioUser", portfolioUser);
    model.addAttribute("tweets",twitterUseCase.getLatestTweets(portfolioUser.getTwitterUser()));
    return "index";
  }

  @GetMapping(path = "/getProfile", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PortfolioUser> getProfilePortfolioUser(@RequestParam String email) {
    PortfolioUser portfolioUser = portfolioUseCase.loadPortfolioUser(email)
        .orElseThrow(() -> new ResourceNotFoundException("", "User not found with email " + email));;
    return ResponseEntity.ok(portfolioUser);
  }

  @PutMapping(path = "/modifyProfile")
  public ResponseEntity<PortfolioUser> modifyProfile(@RequestParam String email, @RequestBody PortfolioUser portfolioUserRequest) {
    PortfolioUser portfolioUser = portfolioUseCase.loadPortfolioUser(email)
        .orElseThrow(() -> new ResourceNotFoundException("", "User not found with email " + email));

    portfolioUser.setAddress(portfolioUserRequest.getAddress());
    portfolioUser.setExperience(portfolioUserRequest.getExperience());
    portfolioUserRequest.setTwitterUser(portfolioUserRequest.getTwitterUser());
    portfolioUser.setName(portfolioUserRequest.getName());
    portfolioUser.setPhone(portfolioUserRequest.getPhone());
    portfolioUser.setZipCode(portfolioUserRequest.getZipCode());
    PortfolioUser savePortfolioUser = portfolioUseCase.updatePortfolioUser(portfolioUser);
    return ResponseEntity.ok(savePortfolioUser);
  }
}
