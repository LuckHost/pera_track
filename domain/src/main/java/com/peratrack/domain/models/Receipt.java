package com.peratrack.domain.models;

import java.util.Date;

public final class Receipt {
    public Integer uid;
    public Date date;
    public String storeName;
    public Float totalAmount;

    public Receipt(
            Integer uid,
            Date date,
            String storeName,
            Float totalAmount
    ) {
        this.uid = uid;
        this.date = date;
        this.storeName = storeName;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        if(totalAmount != null) {
            return "{" + uid.toString() + " " +
                    date.toString() + " "  +
                    storeName + " "  +
                    totalAmount.toString() + "} ";
        }
        return "{" + uid.toString() + " " +
                date.toString() + " "  +
                storeName + " "  +
                "Total amount is not specified} ";

    }
}
