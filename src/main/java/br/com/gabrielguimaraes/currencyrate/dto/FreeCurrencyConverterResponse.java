package br.com.gabrielguimaraes.currencyrate.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.gabrielguimaraes.currencyrate.serializer.FreeCurrencyConverterDeserializer;
import br.com.gabrielguimaraes.currencyrate.util.Currency;

@JsonDeserialize(using = FreeCurrencyConverterDeserializer.class)
public class FreeCurrencyConverterResponse {
	private String id;
	private BigDecimal val;
	private Currency to;
	private Currency fr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getVal() {
		return val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public Currency getFr() {
		return fr;
	}

	public void setFr(Currency fr) {
		this.fr = fr;
	}
}
