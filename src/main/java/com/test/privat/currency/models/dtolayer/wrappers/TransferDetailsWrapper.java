package com.test.privat.currency.models.dtolayer.wrappers;

public class TransferDetailsWrapper {

    private String sourceCurrency;
    private String destinationCurrency;
    private String transferRate;
    private String sourcePay;
    private String destinationReceive;

    public TransferDetailsWrapper() {
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    public String getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(String transferRate) {
        this.transferRate = transferRate;
    }

    public String getSourcePay() {
        return sourcePay;
    }

    public void setSourcePay(String sourcePay) {
        this.sourcePay = sourcePay;
    }

    public String getDestinationReceive() {
        return destinationReceive;
    }

    public void setDestinationReceive(String destinationReceive) {
        this.destinationReceive = destinationReceive;
    }
}
