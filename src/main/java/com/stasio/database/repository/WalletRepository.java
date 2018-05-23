package com.stasio.database.repository;

import com.stasio.database.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet getById(Long id);

    Wallet getByWalletNr(String walletNr);

    @Override
    List<Wallet> findAll();
}
