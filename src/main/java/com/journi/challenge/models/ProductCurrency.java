package com.journi.challenge.models;

public class ProductCurrency extends Product{

    private String currencyCode = "EUR";

    public ProductCurrency(String id, String description, Double price, String currencyCode) {
        super(id, description, price);
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
