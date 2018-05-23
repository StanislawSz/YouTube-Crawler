package com.stasio.database.controller;


import com.stasio.database.model.Wallet;
import com.stasio.database.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/wallet")
public class WalletController {


    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @RequestMapping(value = "/get/allwallets", method = RequestMethod.GET)
    public ResponseEntity<List<Wallet>> getAll() {
        return new ResponseEntity<>(walletService.findAll(), new HttpHeaders(), HttpStatus.OK);
    }
}
