package com.test.privat.currency.models.dtolayer.wrappers;

public class WalletDetailsWrapper {
    private String key;
    private String balance;
    private boolean block;

    public WalletDetailsWrapper() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }
}
