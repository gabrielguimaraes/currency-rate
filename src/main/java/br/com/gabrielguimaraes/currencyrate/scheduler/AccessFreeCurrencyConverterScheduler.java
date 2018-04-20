package br.com.gabrielguimaraes.currencyrate.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.gabrielguimaraes.currencyrate.client.FreeCurrencyConverterClient;
import br.com.gabrielguimaraes.currencyrate.converter.CurrencyRateConverter;
import br.com.gabrielguimaraes.currencyrate.dto.FreeCurrencyConverterResponse;
import br.com.gabrielguimaraes.currencyrate.entity.CurrencyRate;
import br.com.gabrielguimaraes.currencyrate.service.CurrencyRateService;
import br.com.gabrielguimaraes.currencyrate.util.Currency;

@Component
public class AccessFreeCurrencyConverterScheduler {
	private final static Logger LOG = LoggerFactory.getLogger(AccessFreeCurrencyConverterScheduler.class);

	private FreeCurrencyConverterClient freeCurrencyConverterClient;
	private CurrencyRateService currencyRateService;
	private CurrencyRateConverter currencyRateConverter;
	
	@Autowired
	public AccessFreeCurrencyConverterScheduler(FreeCurrencyConverterClient freeCurrencyConverterClient,
			CurrencyRateService currencyRateService) {
		this.freeCurrencyConverterClient = freeCurrencyConverterClient;
		this.currencyRateService = currencyRateService;
		this.currencyRateConverter = new CurrencyRateConverter();
	}


	@Scheduled(fixedRateString = "${currency.rate.frequency}")
	public void fetchCurrencyRatesFromFreeCurrencyConverter() {
		LOG.info("Fetching new rates from FreeCurrencyConverter API");
		FreeCurrencyConverterResponse currentFromFreeCurrencyConverter = freeCurrencyConverterClient.getCurrentCurrencyRate(Currency.EUR, Currency.USD);
		CurrencyRate currencyRateConverted = currencyRateConverter.convert(currentFromFreeCurrencyConverter);
		currencyRateService.save(currencyRateConverted);
	}

}
