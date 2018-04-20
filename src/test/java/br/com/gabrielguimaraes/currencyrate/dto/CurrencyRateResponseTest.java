package br.com.gabrielguimaraes.currencyrate.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gabrielguimaraes.currencyrate.util.Currency;
import br.com.gabrielguimaraes.currencyrate.util.DateTimeUtil;

@RunWith(SpringRunner.class)
@JsonTest
public class CurrencyRateResponseTest {
	
	@Autowired
	private JacksonTester<CurrencyRateResponse> jsonCurrencyRate;
	
	@Test
	public void testSerializationOfCurrencyRateResponse() throws IOException {
		LocalDateTime dateTime = DateTimeUtil.parseStringLocalDateTime("2018-04-19T19:00:01");
		CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse(
				Currency.EUR
				, Currency.USD
				, new BigDecimal("1.54321")
				, dateTime
				);
		
		Assertions.assertThat(this.jsonCurrencyRate.write(currencyRateResponse)).extractingJsonPathStringValue("@.from").isEqualTo("EUR");
		Assertions.assertThat(this.jsonCurrencyRate.write(currencyRateResponse)).extractingJsonPathStringValue("@.to").isEqualTo("USD");
		Assertions.assertThat(this.jsonCurrencyRate.write(currencyRateResponse)).extractingJsonPathNumberValue("@.value").isEqualTo(1.54321);
		Assertions.assertThat(this.jsonCurrencyRate.write(currencyRateResponse)).extractingJsonPathStringValue("@.date").isEqualTo("2018-04-19T19:00:01");
	}
}
