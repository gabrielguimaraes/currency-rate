package br.com.gabrielguimaraes.currencyrate.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielguimaraes.currencyrate.entity.CurrencyRate;
import br.com.gabrielguimaraes.currencyrate.repository.CurrencyRateRepository;
import br.com.gabrielguimaraes.currencyrate.util.Currency;

@Service
public class CurrencyRateService {

	private CurrencyRateRepository currencyRateRepository;

	@Autowired
	public CurrencyRateService(CurrencyRateRepository currencyRateRepository) {
		this.currencyRateRepository = currencyRateRepository;
	}

	public Optional<CurrencyRate> findLatestCurrencyRateFromEURToUSD() {
		return currencyRateRepository.findTopByFromCurrencyAndToCurrencyOrderByCreationDateDesc(Currency.EUR,
				Currency.USD);
	}

	public List<CurrencyRate> findCurrencyRateBetweenStartDateAndEndDateFromEURToUSD(final LocalDateTime startDate,
			final LocalDateTime endDate) {
		return currencyRateRepository
				.findByFromCurrencyAndToCurrencyAndCreationDateGreaterThanEqualAndCreationDateLessThanEqual(
						Currency.EUR, Currency.USD, startDate, endDate);
	}

	public CurrencyRate save(CurrencyRate currencyRate) {
		return this.currencyRateRepository.save(currencyRate);
	}
}
