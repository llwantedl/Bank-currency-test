package com.test.privat.currency.models.dtolayer.wrappers;

public class TransferWrapper {

    private String transferRate;
    private String sourcePay;
    private String destinationReceive;
    private String commissionPercentage;
    private String commissionValue;

    public TransferWrapper() {
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

    public String getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(String commission) {
        this.commissionPercentage = commission;
    }

    public String getCommissionValue() {
        return commissionValue;
    }

    public void setCommissionValue(String commissionValue) {
        this.commissionValue = commissionValue;
    }
}
