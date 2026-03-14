package com.github.hughniblett.marketplace.state.service.impl;

import com.github.hughniblett.marketplace.state.entity.Currencies;
import com.github.hughniblett.marketplace.state.model.CreateCurrencyRequest;
import com.github.hughniblett.marketplace.state.model.CurrencyResponse;
import com.github.hughniblett.marketplace.state.repository.CurrenciesRepository;
import com.github.hughniblett.marketplace.state.repository.WalletsRepository;
import com.github.hughniblett.marketplace.state.service.CurrenciesService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrenciesServiceImpl implements CurrenciesService {
  private final CurrenciesRepository currenciesRepository;
  private final WalletsRepository walletsRepository;

  public ResponseEntity<CurrencyResponse> getCurrency(BigDecimal currencyId) {
    long currencyIdLong = currencyId.longValue();
    Currencies currency = currenciesRepository.findById(currencyIdLong);
    CurrencyResponse currencyResponse = new CurrencyResponse(new BigDecimal(currency.getId()), currency.getName());
    return ResponseEntity.ok(currencyResponse);
  }

  public ResponseEntity<CurrencyResponse> createCurrency(CreateCurrencyRequest createCurrencyRequest) {
    Currencies currency = currenciesRepository.save(new Currencies(createCurrencyRequest.getName()));
    CurrencyResponse currencyResponse = new CurrencyResponse(new BigDecimal(currency.getId()), currency.getName());
    return ResponseEntity.ok(currencyResponse);
  }

  public ResponseEntity<Void> deleteCurrency(BigDecimal currencyId) {
    long currencyIdLong = currencyId.longValue();
    walletsRepository.deleteByWalletsIdCurrencyId(currencyIdLong);
    currenciesRepository.deleteById(currencyIdLong);
    return ResponseEntity.ok().build();
  }
}
