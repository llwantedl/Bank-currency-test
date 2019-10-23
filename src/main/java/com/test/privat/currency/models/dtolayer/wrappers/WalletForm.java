package com.test.privat.currency.models.dtolayer.wrappers;

public class WalletForm {
    private String ownerUsername;

    public WalletForm(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public WalletForm() {
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
