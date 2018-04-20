package br.com.gabrielguimaraes.currencyrate.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gabrielguimaraes.currencyrate.util.Currency;

public class CurrencyRateResponse {
	private Currency from;
	private Currency to;
	private BigDecimal value;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime date;
	
	public CurrencyRateResponse(Currency from, Currency to, BigDecimal value, LocalDateTime date) {
		this.from = from;
		this.to = to;
		this.value = value;
		this.date = date;
	}

	public Currency getFrom() {
		return from;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
