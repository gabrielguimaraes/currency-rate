package br.com.gabrielguimaraes.currencyrate.client;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gabrielguimaraes.currencyrate.dto.FreeCurrencyConverterResponse;
import br.com.gabrielguimaraes.currencyrate.util.Currency;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreeCurrencyConverterClientTest {

	@Autowired
	private FreeCurrencyConverterClient freeCurrencyConverterClient;
	
	@Test
	public void checkIfMappingIsCorrect() {
		FreeCurrencyConverterResponse freeCurrencyConverterResponse = freeCurrencyConverterClient.getCurrentCurrencyRate(Currency.EUR, Currency.USD);
		
		Assertions.assertThat(freeCurrencyConverterResponse).isNotNull();
		Assertions.assertThat(freeCurrencyConverterResponse.getFr()).isEqualTo(Currency.EUR);
		Assertions.assertThat(freeCurrencyConverterResponse.getTo()).isEqualTo(Currency.USD);
	}

}
