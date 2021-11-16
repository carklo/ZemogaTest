package com.zemoga.test.adapters.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.test.application.service.PortfolioService;
import com.zemoga.test.domain.model.PortfolioUser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class PortfolioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PortfolioService portfolioService;

  @Test
  void index() throws Exception {
    PortfolioUser expectedResponse = PortfolioUser.builder().build();
    when(portfolioService.loadPortfolioUser(anyString())).thenReturn(java.util.Optional.ofNullable(expectedResponse));
    mockMvc.perform(get("/?email=santidils7@gmail.com"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/html;charset=UTF-8"));
  }

  @Test
  void getProfilePortfolioUser() throws Exception {
    PortfolioUser expectedResponse = PortfolioUser.builder().build();
    when(portfolioService.loadPortfolioUser(anyString())).thenReturn(java.util.Optional.ofNullable(expectedResponse));
    mockMvc.perform(get("/getProfile?email=santidils7@gmail.com"))
        .andExpect(status().isOk())
        .andExpect(content().json(toJsonString(expectedResponse)));
  }

  @Test
  void getProfilePortfolioUserException() {
    assertThrows(NestedServletException.class,
        ()-> mockMvc.perform(get("/getProfile?email=testnotfound@gmail.com"))
            .andExpect(status().is5xxServerError()));
  }

  @Test
  void modifyProfile() throws Exception {
    PortfolioUser expectedResponse = PortfolioUser.builder().build();
    when(portfolioService.loadPortfolioUser(anyString())).thenReturn(java.util.Optional.ofNullable(expectedResponse));
    when(portfolioService.updatePortfolioUser(any())).thenReturn(expectedResponse);
    mockMvc.perform(put("/modifyProfile?email=santidils7@gmail.com")
            .content(readAsJsonString("updateProfile.json"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(toJsonString(expectedResponse)));
  }

  private String toJsonString(Object object) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(object);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected String readAsJsonString(String fileName) {
    try {
      return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
