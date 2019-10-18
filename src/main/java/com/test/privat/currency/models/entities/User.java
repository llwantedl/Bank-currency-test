package com.test.privat.currency.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private Currency valueCurrency;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Wallet> wallets;

    public User() {
        roles = new HashSet<>();
        wallets = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Currency getValueCurrency() {
        return valueCurrency;
    }

    public void setValueCurrency(Currency valueCurrency) {
        this.valueCurrency = valueCurrency;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Wallet> getWallets() {
        return wallets;
    }

    public void setWallet(Wallet wallet) {
        this.wallets.add(wallet);
    }

    public void setWallets(Set<Wallet> wallets) {
        this.wallets = wallets;
    }
}
