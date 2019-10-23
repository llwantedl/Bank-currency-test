package com.test.privat.currency.models.dtolayer.wrappers;

public class OperationWrapper {
    private String key;
    private String sourceWalletKey;
    private String destinationWalletKey;
    private String amount;
    private String sourceCurrencyKey;
    private String destinationCurrencyKey;
    private String rate;
    private String sourceUser;
    private String destinationUser;
    private String transferDate;
    private boolean isOutgoing;

    public OperationWrapper() {
    }

    public String getDestinationWalletKey() {
        return destinationWalletKey;
    }

    public void setDestinationWalletKey(String destinationWalletKey) {
        this.destinationWalletKey = destinationWalletKey;
    }

    public String getSourceWalletKey() {
        return sourceWalletKey;
    }

    public void setSourceWalletKey(String sourceWalletKey) {
        this.sourceWalletKey = sourceWalletKey;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSourceCurrencyKey() {
        return sourceCurrencyKey;
    }

    public void setSourceCurrencyKey(String sourceCurrencyKey) {
        this.sourceCurrencyKey = sourceCurrencyKey;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

    public String getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(String destinationUser) {
        this.destinationUser = destinationUser;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDestinationCurrencyKey() {
        return destinationCurrencyKey;
    }

    public void setDestinationCurrencyKey(String destinationCurrencyKey) {
        this.destinationCurrencyKey = destinationCurrencyKey;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public boolean isOutgoing() {
        return isOutgoing;
    }

    public void setOutgoing(boolean outgoing) {
        isOutgoing = outgoing;
    }
}
