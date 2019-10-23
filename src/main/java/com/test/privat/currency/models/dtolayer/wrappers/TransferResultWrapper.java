package com.test.privat.currency.models.dtolayer.wrappers;

public class TransferResultWrapper {
    private int resultCode;
    private String message;

    private TransferResultWrapper(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static TransferResultWrapper build(int resultCode, String message){
        return new TransferResultWrapper(resultCode, message);
    }
}
