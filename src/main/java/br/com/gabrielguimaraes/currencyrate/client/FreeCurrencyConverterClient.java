package br.com.gabrielguimaraes.currencyrate.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gabrielguimaraes.currencyrate.dto.FreeCurrencyConverterResponse;
import br.com.gabrielguimaraes.currencyrate.util.Currency;

@Component
public class FreeCurrencyConverterClient {
	private final static Logger LOG = LoggerFactory.getLogger(FreeCurrencyConverterClient.class);
	private final static String FREE_CURRENCY_URI = "https://free.currencyconverterapi.com/api/v5/convert?q={currencies}";
	
	private RestTemplate restTemplate;

	public FreeCurrencyConverterClient() {
		this.restTemplate = new RestTemplate();
	}
	

	public FreeCurrencyConverterResponse getCurrentCurrencyRate(Currency fromCurrency, Currency toCurrency) {
			String queryValue = fromCurrency + "_" + toCurrency;
			FreeCurrencyConverterResponse freeCurrencyConverterResponse = restTemplate.getForObject(FREE_CURRENCY_URI, FreeCurrencyConverterResponse.class, queryValue);
			
			return freeCurrencyConverterResponse;
	}


	
	
}
