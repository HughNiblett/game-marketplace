package com.github.hughniblett.marketplace.state.service;

import com.github.hughniblett.marketplace.state.model.CreateCurrencyRequest;
import com.github.hughniblett.marketplace.state.model.CurrencyResponse;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;

public interface CurrenciesService {
  public ResponseEntity<CurrencyResponse> getCurrency(BigDecimal currencyId);
  public ResponseEntity<CurrencyResponse> createCurrency(CreateCurrencyRequest createCurrencyRequest);
  public ResponseEntity<Void> deleteCurrency(BigDecimal currencyId);
}
