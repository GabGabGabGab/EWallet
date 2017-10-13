package com.example.user.ewallet.database;

/**
 * Created by Gabb on 13/10/2017.
 */

public class Wallet {
    private String WalletID;
    private double Balance;
    private int LoyaltyPoint;
    private String LoginPassword;

    public Wallet (String WalletID, double Balance, int LoyaltyPoint, String LoginPassword){
        this.setWalletID(WalletID);
        this.setBalance(Balance);
        this.setLoyaltyPoint(LoyaltyPoint);
        this.setLoginPassword(LoginPassword);
    }

    public String getWalletID() {
        return WalletID;
    }

    public void setWalletID(String walletID) {
        WalletID = walletID;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public int getLoyaltyPoint() {
        return LoyaltyPoint;
    }

    public void setLoyaltyPoint(int loyaltyPoint) {
        LoyaltyPoint = loyaltyPoint;
    }

    public String getLoginPassword() {
        return LoginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        LoginPassword = loginPassword;
    }
}
