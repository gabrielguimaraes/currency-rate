package br.com.gabrielguimaraes.currencyrate.v1.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielguimaraes.currencyrate.converter.CurrencyRateResponseConverter;
import br.com.gabrielguimaraes.currencyrate.dto.CurrencyRateResponse;
import br.com.gabrielguimaraes.currencyrate.entity.CurrencyRate;
import br.com.gabrielguimaraes.currencyrate.service.CurrencyRateService;
import br.com.gabrielguimaraes.currencyrate.util.DateTimeUtil;

@RestController
@RequestMapping("/currencies/api/v1")
public class CurrencyRateRest {

	private CurrencyRateService currencyRateService;
	private CurrencyRateResponseConverter currencyRateResponseConverter;

	@Autowired
	public CurrencyRateRest(CurrencyRateService currencyRateService) {
		this.currencyRateService = currencyRateService;
		this.currencyRateResponseConverter = new CurrencyRateResponseConverter();
	}


	@GetMapping(value = "/rates", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CurrencyRateResponse getLatestCurrencyRateFromEURToUSD() {
		Optional<CurrencyRate> currencyRateOptional = currencyRateService.findLatestCurrencyRateFromEURToUSD();
		if (currencyRateOptional.isPresent()) {
			return currencyRateResponseConverter.convert(currencyRateOptional.get());
		}
			
		return null;
	}
	
	
	@GetMapping(value = "/rates", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, params = {"startDate", "endDate"})
	public List<CurrencyRateResponse> getCurrencyRateByDateTimeIntervalFromEURToUSD(@RequestParam("startDate") String startDateParam, @RequestParam("endDate") String endDateParam) {
		LocalDateTime startDate = DateTimeUtil.parseStringLocalDateTime(startDateParam);
		LocalDateTime endDate = DateTimeUtil.parseStringLocalDateTime(endDateParam);
		
		List<CurrencyRate> currencyRateList = currencyRateService.findCurrencyRateBetweenStartDateAndEndDateFromEURToUSD(startDate, endDate);
		if (currencyRateList.isEmpty()) {
			return null;
		}
			
		return currencyRateResponseConverter.convertFromList(currencyRateList);
	}
	
}
