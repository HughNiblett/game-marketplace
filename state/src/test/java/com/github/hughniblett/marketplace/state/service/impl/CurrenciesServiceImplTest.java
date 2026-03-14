package com.github.hughniblett.marketplace.state.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.github.hughniblett.marketplace.state.entity.Currencies;
import com.github.hughniblett.marketplace.state.model.CreateCurrencyRequest;
import com.github.hughniblett.marketplace.state.model.CurrencyResponse;
import com.github.hughniblett.marketplace.state.repository.CurrenciesRepository;
import com.github.hughniblett.marketplace.state.repository.WalletsRepository;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CurrenciesServiceImplTest {

  @Mock private CurrenciesRepository currenciesRepository;
  @Mock private WalletsRepository walletsRepository;
  @InjectMocks private CurrenciesServiceImpl currenciesService;

  private Currencies currency;

  @BeforeEach
  void setUp() throws Exception {
    currency = new Currencies("Gold");
    Field idField = Currencies.class.getDeclaredField("id");
    idField.setAccessible(true);
    idField.set(currency, 1L);
  }

  @Test
  void getCurrency_returnsOkWithCorrectBody() {
    when(currenciesRepository.findById(1L)).thenReturn(currency);

    ResponseEntity<CurrencyResponse> response = currenciesService.getCurrency(BigDecimal.ONE);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getId()).isEqualByComparingTo(BigDecimal.ONE);
    assertThat(response.getBody().getName()).isEqualTo("Gold");
    verify(currenciesRepository).findById(1L);
  }

  @Test
  void createCurrency_savesEntityAndReturnsOkWithCorrectBody() {
    when(currenciesRepository.save(any(Currencies.class))).thenReturn(currency);

    ResponseEntity<CurrencyResponse> response =
        currenciesService.createCurrency(new CreateCurrencyRequest("Gold"));

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getId()).isEqualByComparingTo(BigDecimal.ONE);
    assertThat(response.getBody().getName()).isEqualTo("Gold");
    verify(currenciesRepository).save(any(Currencies.class));
  }

  @Test
  void deleteCurrency_deletesWalletsAndCurrencyAndReturnsOk() {
    ResponseEntity<Void> response = currenciesService.deleteCurrency(BigDecimal.ONE);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    verify(walletsRepository).deleteByWalletsIdCurrencyId(1L);
    verify(currenciesRepository).deleteById(1L);
  }

  @Test
  void deleteCurrency_deletesWalletsBeforeCurrency() {
    currenciesService.deleteCurrency(BigDecimal.ONE);

    InOrder inOrder = inOrder(walletsRepository, currenciesRepository);
    inOrder.verify(walletsRepository).deleteByWalletsIdCurrencyId(1L);
    inOrder.verify(currenciesRepository).deleteById(1L);
  }

  @Test
  void createCurrency_savesEntityWithCorrectName() {
    when(currenciesRepository.save(any(Currencies.class))).thenReturn(currency);

    currenciesService.createCurrency(new CreateCurrencyRequest("Gold"));

    verify(currenciesRepository).save(argThat(c -> c.getName().equals("Gold")));
  }
}

