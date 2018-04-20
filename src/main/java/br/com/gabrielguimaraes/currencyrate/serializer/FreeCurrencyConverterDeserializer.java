package br.com.gabrielguimaraes.currencyrate.serializer;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.gabrielguimaraes.currencyrate.dto.FreeCurrencyConverterResponse;
import br.com.gabrielguimaraes.currencyrate.util.Currency;

public class FreeCurrencyConverterDeserializer extends StdDeserializer<FreeCurrencyConverterResponse> {
	
	public FreeCurrencyConverterDeserializer() {
		this(null);
	}
	
	public FreeCurrencyConverterDeserializer(Class<?> vc) {
		super(vc);
	}

	private static final long serialVersionUID = -4120753854172510255L;

	@Override
	public FreeCurrencyConverterResponse deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		JsonNode rootNode = jp.getCodec().readTree(jp);
		JsonNode currencyRatesResultsNode = rootNode.get("results");
		
		
		FreeCurrencyConverterResponse freeCurrencyConverterResponse = new FreeCurrencyConverterResponse();
		freeCurrencyConverterResponse.setId(currencyRatesResultsNode.findPath("id").asText());
		freeCurrencyConverterResponse.setTo(Currency.valueOf(currencyRatesResultsNode.findPath("to").asText()));
		freeCurrencyConverterResponse.setFr(Currency.valueOf(currencyRatesResultsNode.findPath("fr").asText()));
		freeCurrencyConverterResponse.setVal(new BigDecimal(currencyRatesResultsNode.findPath("val").asText()));
		
		return freeCurrencyConverterResponse;
	}

}
