package com.stasio.database.service;

import com.stasio.database.model.Film;
import com.stasio.database.model.User;
import com.stasio.database.model.Wallet;
import com.stasio.database.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("walletService")
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Wallet getWalletById(Long id) {
        return walletRepository.getById(id);
    }

    @Transactional
    public Wallet getByWalletNr(String number) {
        return walletRepository.getByWalletNr(number);

    }

    @Transactional
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    public void saveWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Transactional
    public void addWallet(String walletNr, User user) {
        walletRepository.save(new Wallet(walletNr, user));
    }

    @Transactional
    public Wallet createIfNotExist(String walletNr, User user, Film film) {
        Wallet wallet;
//        System.err.println("wypisuje \n" + walletNr + " " + user.toString() + " " + film.getTitle().toString());
        try {
            wallet = walletRepository.getByWalletNr(walletNr);
            wallet.addFilm(film);

        } catch (NullPointerException e) {
            wallet = new Wallet(walletNr, user);
            wallet.addFilm(film);

        }
        walletRepository.save(wallet);
        return wallet;
    }

}
