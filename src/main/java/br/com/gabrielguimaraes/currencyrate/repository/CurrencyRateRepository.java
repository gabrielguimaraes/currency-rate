package br.com.gabrielguimaraes.currencyrate.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gabrielguimaraes.currencyrate.entity.CurrencyRate;
import br.com.gabrielguimaraes.currencyrate.util.Currency;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Integer> {

	Optional<CurrencyRate> findTopByFromCurrencyAndToCurrencyOrderByCreationDateDesc(Currency fromCurrency, Currency toCurrency);

	List<CurrencyRate> findByFromCurrencyAndToCurrencyAndCreationDateGreaterThanEqualAndCreationDateLessThanEqual(
			Currency fromCurrency
			, Currency toCurrency
			, LocalDateTime startDate
			, LocalDateTime endDate);

	
}
