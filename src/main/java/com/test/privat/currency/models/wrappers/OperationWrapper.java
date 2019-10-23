package com.test.privat.currency.models.wrappers;

import com.test.privat.currency.models.entities.Wallet;

import java.math.BigDecimal;

public class OperationWrapper {
    private Wallet sourceWallet;
    private Wallet destinationWallet;
    private BigDecimal rate;
    private BigDecimal commission;
    private BigDecimal amount;

    public OperationWrapper() {
    }

    public Wallet getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(Wallet sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public Wallet getDestinationWallet() {
        return destinationWallet;
    }

    public void setDestinationWallet(Wallet destinationWallet) {
        this.destinationWallet = destinationWallet;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
