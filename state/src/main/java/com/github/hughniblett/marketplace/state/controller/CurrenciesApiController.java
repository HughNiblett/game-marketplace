package com.github.hughniblett.marketplace.state.controller;

import com.github.hughniblett.marketplace.state.controllers.CurrenciesApi;
import com.github.hughniblett.marketplace.state.model.CreateCurrencyRequest;
import com.github.hughniblett.marketplace.state.model.CurrencyResponse;
import com.github.hughniblett.marketplace.state.service.CurrenciesService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "marketplace/state/v1", produces = "application/json")
@RequiredArgsConstructor
public class CurrenciesApiController implements CurrenciesApi {
  private final CurrenciesService currenciesService;
  private final Logger log = LoggerFactory.getLogger(CurrenciesApiController.class);

  @Override
  public ResponseEntity<CurrencyResponse> getCurrency(BigDecimal currencyId) {
    log.info("[Request Received] Get currency: {}", currencyId);
    return currenciesService.getCurrency(currencyId);
  }

  @Override
  public ResponseEntity<CurrencyResponse> createCurrency(CreateCurrencyRequest createCurrencyRequest) {
    log.info("[Request Received] Create currency: {}", createCurrencyRequest.getName());
    return currenciesService.createCurrency(createCurrencyRequest);
  }

  @Override
  public ResponseEntity<Void> deleteCurrency(BigDecimal currencyId) {
    log.info("[Request Received] Delete currency: {}", currencyId);
    return currenciesService.deleteCurrency(currencyId);
  }
}
