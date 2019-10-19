package com.test.privat.currency.models.dtolayer.wrappers;

public class UserDetailsWrapper {
    private String login;
    private String email;
    private String currencyKey;

    public UserDetailsWrapper() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrencyKey() {
        return currencyKey;
    }

    public void setCurrencyKey(String currencyKey) {
        this.currencyKey = currencyKey;
    }
}
