package com.peratrack.domain.models;

import java.util.Date;

public final class Receipt {
    public Date date;
    public String storeName;
    public Float totalAmount;

    public Receipt() {
        this.date = new Date();
        this.storeName = "No storage entered";
        this.totalAmount = null;
    }

    public Receipt(Date date, String storeName, Float totalAmount) {
        this.date = date;
        this.storeName = storeName;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        if(totalAmount != null) {
            return " {" + date.toString() + "} " +
                    " {" + storeName + "} "  +
                    " {" + totalAmount.toString() + "} ";
        }
        return " {" + date.toString() + "} " +
                " {" + storeName + "} "  +
                " {Total amount is not specified} ";

    }
}
