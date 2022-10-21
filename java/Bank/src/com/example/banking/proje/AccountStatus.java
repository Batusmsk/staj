package com.example.banking.proje;

public enum AccountStatus {
    ACTIVE(100), CLOSED(200);

    private int code;
    private AccountStatus(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
