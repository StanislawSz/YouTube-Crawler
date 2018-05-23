package com.stasio.database.controller;


import com.stasio.database.model.Wallet;
import com.stasio.database.service.TransactionService;
import com.stasio.database.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final WalletService walletService;

    @Autowired
    public TransactionController(TransactionService transactionService, WalletService walletService) {
        this.transactionService = transactionService;
        this.walletService = walletService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpStatus add(@RequestParam String outWallet, @RequestParam String inWallet, @RequestParam double amount, @RequestParam int type) {
        Wallet walletOut = walletService.getByWalletNr(outWallet);
        transactionService.addTransaction(walletOut, inWallet, amount, type);
        walletOut.setBalance(walletOut.getBalance() - amount);
        walletService.saveWallet(walletOut);
        return HttpStatus.OK;
    }
}
