package com.test.privat.currency.models.dtolayer.wrappers;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "exchangerate")
public class RateWrapper {
    private String baseCurrency;
    private String currency;
    private double saleRateNB;
    private double purchaseRateNB;
    private double saleRate;
    private double purchaseRate;

    public RateWrapper() {
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    @XmlAttribute
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    @XmlAttribute
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getSaleRateNB() {
        return saleRateNB;
    }

    @XmlAttribute
    public void setSaleRateNB(double saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public double getPurchaseRateNB() {
        return purchaseRateNB;
    }

    @XmlAttribute
    public void setPurchaseRateNB(double purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public double getSaleRate() {
        return saleRate;
    }

    @XmlAttribute
    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    public double getPurchaseRate() {
        return purchaseRate;
    }

    @XmlAttribute
    public void setPurchaseRate(double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }
}
