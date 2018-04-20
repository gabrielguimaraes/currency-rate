package br.com.gabrielguimaraes.currencyrate.scheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gabrielguimaraes.currencyrate.client.FreeCurrencyConverterClient;
import br.com.gabrielguimaraes.currencyrate.dto.FreeCurrencyConverterResponse;
import br.com.gabrielguimaraes.currencyrate.entity.CurrencyRate;
import br.com.gabrielguimaraes.currencyrate.repository.CurrencyRateRepository;
import br.com.gabrielguimaraes.currencyrate.service.CurrencyRateService;
import br.com.gabrielguimaraes.currencyrate.util.Currency;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessFreeCurrencyConverterSchedulerTest {

	@Mock
	private FreeCurrencyConverterClient freeCurrencyConverterClientMock;
	
	private AccessFreeCurrencyConverterScheduler accessFreeCurrencyConverterScheduler;
	
	@Autowired
	private CurrencyRateService currencyRateService;
	
	@Autowired
	private CurrencyRateRepository currencyRateRepository;
	
	@Before
	public void setUp() {
		currencyRateRepository.deleteAll();
		accessFreeCurrencyConverterScheduler = new AccessFreeCurrencyConverterScheduler(freeCurrencyConverterClientMock, currencyRateService);
	}
	
	@Test
	public void whenFetchingCorrectlyFromFreeCurrencyConverterCheckIfIsStoredIntoDatabaseAfter() {
		final FreeCurrencyConverterResponse basicFreeCurrencyConverter = createBasicFreeCurrencyConverterResponse();
		when(freeCurrencyConverterClientMock.getCurrentCurrencyRate(any(), any())).thenReturn(basicFreeCurrencyConverter);
		
		accessFreeCurrencyConverterScheduler.fetchCurrencyRatesFromFreeCurrencyConverter();
		
		List<CurrencyRate> allCurrenciesRates = currencyRateRepository.findAll();
		
		Assertions.assertThat(allCurrenciesRates.get(0)).isNotNull();
		Assertions.assertThat(allCurrenciesRates.get(0).getFromCurrency()).isEqualTo(basicFreeCurrencyConverter.getFr());
		Assertions.assertThat(allCurrenciesRates.get(0).getToCurrency()).isEqualTo(basicFreeCurrencyConverter.getTo());
		Assertions.assertThat(allCurrenciesRates.get(0).getValue()).isEqualTo(basicFreeCurrencyConverter.getVal());
	}
	
	private FreeCurrencyConverterResponse createBasicFreeCurrencyConverterResponse() {
		FreeCurrencyConverterResponse freeCurrencyConverter = new FreeCurrencyConverterResponse();
		freeCurrencyConverter.setFr(Currency.EUR);
		freeCurrencyConverter.setTo(Currency.USD);
		freeCurrencyConverter.setVal(new BigDecimal("2.1023").setScale(6));
		return freeCurrencyConverter;
	}

}
