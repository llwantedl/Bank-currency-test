package com.test.privat.currency.models.dtolayer.wrappers;

import java.math.BigDecimal;

public class TransferForm {
    private String sourceWalletKey;
    private String destinationWalletKey;
    private BigDecimal amount;

    public TransferForm() {
    }

    public String getSourceWalletKey() {
        return sourceWalletKey;
    }

    public void setSourceWalletKey(String sourceWalletKey) {
        this.sourceWalletKey = sourceWalletKey;
    }

    public String getDestinationWalletKey() {
        return destinationWalletKey;
    }

    public void setDestinationWalletKey(String destinationWalletKey) {
        this.destinationWalletKey = destinationWalletKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
