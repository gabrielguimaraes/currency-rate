package br.com.gabrielguimaraes.currencyrate.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gabrielguimaraes.currencyrate.entity.CurrencyRate;
import br.com.gabrielguimaraes.currencyrate.repository.CurrencyRateRepository;
import br.com.gabrielguimaraes.currencyrate.util.Currency;
import br.com.gabrielguimaraes.currencyrate.util.DateTimeUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyRateServiceTest {
	
	@Autowired
	private CurrencyRateService currencyRateService;
	
	@Autowired
	private CurrencyRateRepository currencyRateRepository;
	
	@Before
	public void setUp() {
		currencyRateRepository.deleteAll();
	}
	
	@Test
	public void checkIfLastCurrencyRateIsReturned() throws ParseException {
		createBasicCurrencyRateEntity(DateTimeUtil.parseStringLocalDateTime("2010-01-01T01:01:01"));
		CurrencyRate expectedCurrencyRate = createBasicCurrencyRateEntity(DateTimeUtil.parseStringLocalDateTime("2018-04-19T20:00:10"));
		
		Optional<CurrencyRate> actualCurrencyRate = currencyRateService.findLatestCurrencyRateFromEURToUSD();
		
		Assertions.assertThat(actualCurrencyRate.isPresent()).isTrue();
		Assertions.assertThat(actualCurrencyRate.get().getId()).isEqualTo(expectedCurrencyRate.getId());
	}
	
	
	@Test
	public void checkIfAllCurrenciesRatesAreCorrectUsingInterval() throws ParseException {
		LocalDateTime referenceDateTime = DateTimeUtil.parseStringLocalDateTime("2018-04-30T20:15:00");
		LocalDateTime before10Days = referenceDateTime.minusDays(10);
		LocalDateTime before20Days = referenceDateTime.minusDays(20);
		LocalDateTime before1Month = referenceDateTime.minusMonths(1);
		
		createBasicCurrencyRateEntity(referenceDateTime);
		createBasicCurrencyRateEntity(before10Days);
		createBasicCurrencyRateEntity(before20Days);
		createBasicCurrencyRateEntity(before1Month);
		createBasicCurrencyRateEntity(before10Days, Currency.USD, Currency.EUR);		
		
		List<CurrencyRate> actualCurrencyRateList = currencyRateService.findCurrencyRateBetweenStartDateAndEndDateFromEURToUSD(before20Days, before10Days);
		
		Assertions.assertThat(actualCurrencyRateList.size()).isEqualTo(2);
	}

	private CurrencyRate createBasicCurrencyRateEntity(LocalDateTime referenceDateTime) {
		return createBasicCurrencyRateEntity(referenceDateTime, Currency.EUR, Currency.USD);
	}
	
	private CurrencyRate createBasicCurrencyRateEntity(LocalDateTime referenceDateTime, Currency from, Currency to) {
		CurrencyRate currencyRate = new CurrencyRate();
		currencyRate.setCreationDate(referenceDateTime);
		currencyRate.setFromCurrency(from);
		currencyRate.setToCurrency(to);
		currencyRate.setValue(new BigDecimal("2.10").multiply(new BigDecimal(referenceDateTime.getDayOfMonth())));
		currencyRateRepository.save(currencyRate);
		
		return currencyRate;
	}

}

