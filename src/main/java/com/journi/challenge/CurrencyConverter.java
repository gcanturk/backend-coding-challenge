package com.journi.challenge;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.graalvm.compiler.hotspot.nodes.CurrentJavaThreadNode;
import org.springframework.boot.json.JacksonJsonParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CurrencyConverter {
    private static CurrencyConverter currencyConverter;

    private final Map<String, String> supportedCountriesCurrency;
    private final Map<String, Double> currencyEurRate;

    public static CurrencyConverter getInstance() {
        if (currencyConverter == null)
            currencyConverter = new CurrencyConverter();
        return currencyConverter;
    }

    private CurrencyConverter() {
        supportedCountriesCurrency = new HashMap<>();
        supportedCountriesCurrency.put("AT", "EUR");
        supportedCountriesCurrency.put("DE", "EUR");
        supportedCountriesCurrency.put("HU", "HUF");
        supportedCountriesCurrency.put("GB", "GBP");
        supportedCountriesCurrency.put("FR", "EUR");
        supportedCountriesCurrency.put("PT", "EUR");
        supportedCountriesCurrency.put("IE", "EUR");
        supportedCountriesCurrency.put("ES", "EUR");
        supportedCountriesCurrency.put("BR", "BRL");
        supportedCountriesCurrency.put("US", "USD");
        supportedCountriesCurrency.put("CA", "CAD");

        currencyEurRate = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL rates = getClass().getResource("/eur_rate.json");
            JsonNode ratesTree = mapper.readTree(rates);
            Iterator<JsonNode> currenciesIterator = ratesTree.findPath("currencies").elements();
            currenciesIterator.forEachRemaining(currency -> currencyEurRate.put(currency.get("currency").asText(), currency.get("rate").asDouble()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCurrencyForCountryCode(String countryCode) {
        return supportedCountriesCurrency.getOrDefault(countryCode.toUpperCase(), "EUR");
    }

    public Double convertEurToCurrency(String currencyCode, Double eurValue) {
        return eurValue * currencyEurRate.getOrDefault(currencyCode, 1.0);
    }

    public Double convertCurrencyToEur(String currencyCode, Double value) {
        return value / currencyEurRate.getOrDefault(currencyCode, 1.0);
    }
}
