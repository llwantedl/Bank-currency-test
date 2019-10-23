package com.test.privat.currency.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "WALLET")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "wallet_key")
    private String key;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "destinationWallet")
    private Set<Operation> incomingOperations;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sourceWallet")
    private Set<Operation> outgoingOperations;

    @Column(name = "block")
    private boolean block;

    public Wallet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public Set<Operation> getIncomingOperations() {
        return incomingOperations;
    }

    public void setIncomingOperations(Set<Operation> incomingOperations) {
        this.incomingOperations = incomingOperations;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Operation> getOutgoingOperations() {
        return outgoingOperations;
    }

    public void setOutgoingOperations(Set<Operation> outgoingOperations) {
        this.outgoingOperations = outgoingOperations;
    }
}
