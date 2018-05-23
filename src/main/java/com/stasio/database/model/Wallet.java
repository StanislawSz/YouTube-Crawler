package com.stasio.database.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "wallet_nr")
    private String walletNr;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = User.class)
    private User user;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH,}, targetEntity = Film.class)
    private Set<Film> films;

    public Wallet(String walletNr, User user) {
        this.walletNr = walletNr;
        this.user = user;

        this.balance = 0.00;
    }

    public Wallet() {
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", walletNr='" + walletNr + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                ", films=" + films +
                '}';
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWalletNr() {
        return walletNr;
    }

    public void setWalletNr(String walletNr) {
        this.walletNr = walletNr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        if (this.films == null)
            this.films = new LinkedHashSet<>();

        this.films.add(film);
    }
}
