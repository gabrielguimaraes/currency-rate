package br.com.gabrielguimaraes.currencyrate.dto;

import java.io.IOException;
import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gabrielguimaraes.currencyrate.util.Currency;

@RunWith(SpringRunner.class)
@JsonTest
public class FreeCurrencyConverterResponseTest {

	@Autowired
	JacksonTester<FreeCurrencyConverterResponse> json;
	
	@Test
	public void verifyDeserializerForFreeCurrencyConverter() throws IOException {
		String originalJson = "{\"query\":{\"count\":1},\"results\":{\"EUR_USD\":{\"id\":\"EUR_USD\",\"val\":1.237167,\"to\":\"USD\",\"fr\":\"EUR\"}}}";
		
		ObjectContent<FreeCurrencyConverterResponse> freeCurrencyConverterContent = json.parse(originalJson);
		
		Assertions.assertThat(freeCurrencyConverterContent).isNotNull();
		Assertions.assertThat(freeCurrencyConverterContent.getObject()).isNotNull();
		Assertions.assertThat(freeCurrencyConverterContent.getObject().getId()).isEqualTo("EUR_USD");
		Assertions.assertThat(freeCurrencyConverterContent.getObject().getFr()).isEqualTo(Currency.EUR);
		Assertions.assertThat(freeCurrencyConverterContent.getObject().getTo()).isEqualTo(Currency.USD);
		Assertions.assertThat(freeCurrencyConverterContent.getObject().getVal()).isEqualTo(new BigDecimal("1.237167"));
		
	}

}
