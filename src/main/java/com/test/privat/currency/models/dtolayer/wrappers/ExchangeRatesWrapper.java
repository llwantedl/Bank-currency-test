package com.test.privat.currency.models.dtolayer.wrappers;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "exchangerates")
public class ExchangeRatesWrapper {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;

    @XmlElement(name = "exchangerate")
    private List<RateWrapper> rates = new ArrayList<>();

    public ExchangeRatesWrapper() {
    }

    public String getDate() {
        return date;
    }

    @XmlAttribute
    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    @XmlAttribute
    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    @XmlAttribute(name = "BaseCurrency")
    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    @XmlAttribute(name = "BaseCurrencyLit")
    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public List<RateWrapper> getRates() {
        return rates;
    }
}
