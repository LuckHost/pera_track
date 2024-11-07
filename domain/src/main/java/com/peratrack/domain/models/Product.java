package com.peratrack.domain.models;

public class Product {
    public String name;
    public Float count;
    public CountType countType;
    public Float amount;
    public String currency;

    public enum CountType {
        KILOGRAMS("kilograms"),
        UNITS("units"),
        GRAMS("grams");

        private final String displayName;

        CountType(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public Product(
            String name,
            Float count,
            CountType countType,
            Float amount,
            String currency
    ) {
        this.name = name;
        this.count = count;
        this.countType = countType;
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String toString() {
        if (name != null && count != null && countType != null
                && amount != null && currency != null) {
            return "{" + name + " " + count + " " +
                    countType + " " + amount + " " + currency + "} ";
        }
        return super.toString();
    }
}

