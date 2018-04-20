package br.com.gabrielguimaraes.currencyrate.converter;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;

import br.com.gabrielguimaraes.currencyrate.dto.FreeCurrencyConverterResponse;
import br.com.gabrielguimaraes.currencyrate.entity.CurrencyRate;

public class CurrencyRateConverter implements Converter<FreeCurrencyConverterResponse, CurrencyRate>{

	@Override
	public CurrencyRate convert(FreeCurrencyConverterResponse source) {
		CurrencyRate currencyRate = new CurrencyRate();
		currencyRate.setFromCurrency(source.getFr());
		currencyRate.setToCurrency(source.getTo());
		currencyRate.setValue(source.getVal());
		currencyRate.setCreationDate(LocalDateTime.now());
		return currencyRate;
	}
}
