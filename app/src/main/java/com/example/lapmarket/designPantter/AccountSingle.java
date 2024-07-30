package com.example.lapmarket.designPantter;

import com.example.lapmarket.model.account;

public class AccountSingle {
    private static AccountSingle instance;

    private account account = new account();

    public static AccountSingle getInstance(){
        if (instance == null){
            instance = new AccountSingle();
        }
        return instance;
    }

    public account getAccount() {
        return account;
    }

    public void setAccount(account account) {
        this.account = account;
    }
}
