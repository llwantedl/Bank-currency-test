package com.test.privat.currency.models.security;

public final class Credentials {
    private String login;
    private String password;

    private Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Credentials() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Credentials build(String login, String password){
        return new Credentials(login, password);
    }
}
