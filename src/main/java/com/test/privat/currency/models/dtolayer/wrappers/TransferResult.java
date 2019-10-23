package com.test.privat.currency.models.dtolayer.wrappers;

public enum TransferResult {
    SUCCESS(0),
    VALIDATION_ERROR(1),
    ERROR(2);

    private int code;

    TransferResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
