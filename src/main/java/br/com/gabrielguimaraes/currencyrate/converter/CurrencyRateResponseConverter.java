package br.com.gabrielguimaraes.currencyrate.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import br.com.gabrielguimaraes.currencyrate.dto.CurrencyRateResponse;
import br.com.gabrielguimaraes.currencyrate.entity.CurrencyRate;

public class CurrencyRateResponseConverter implements Converter<CurrencyRate, CurrencyRateResponse>{

	@Override
	public CurrencyRateResponse convert(CurrencyRate source) {
		return new CurrencyRateResponse(
			source.getFromCurrency()
			, source.getToCurrency()
			, source.getValue()
			, source.getCreationDate()
		);
	}

	public List<CurrencyRateResponse> convertFromList(List<CurrencyRate> currencyRateList) {
		ArrayList<CurrencyRateResponse> currencyRateResponseList = new ArrayList<CurrencyRateResponse>();
		if (currencyRateList.isEmpty()) {
			return currencyRateResponseList;
		}
			
		for (CurrencyRate currencyRate : currencyRateList) {
			currencyRateResponseList.add(convert(currencyRate));
		}
		
		return currencyRateResponseList;
	}
}
