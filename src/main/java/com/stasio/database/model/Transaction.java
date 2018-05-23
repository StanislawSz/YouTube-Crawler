package com.stasio.database.model;


import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = Wallet.class)
    private Wallet idWallet;

    @Column(name = "btc")
    private String btc;

    @Column(name = "amount")
    private double amount;

    @Column(name = "type")
    private int type;

    public Transaction() {
    }


    public Transaction(Wallet idWallet, String btc, double amount, int type) {
        this.idWallet = idWallet;
        this.btc = btc;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", idWallet=" + idWallet +
                ", btc='" + btc + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }

    public Wallet getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(Wallet idWallet) {
        this.idWallet = idWallet;
    }

    public String getBtc() {
        return btc;
    }

    public void setBtc(String btc) {
        this.btc = btc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}


