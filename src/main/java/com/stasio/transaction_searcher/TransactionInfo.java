package com.stasio.transaction_searcher;

import org.bitcoinj.core.Coin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TransactionInfo {

    public void function(String adres) {
        BlockExplorer blockExplorer = new BlockExplorer();

        Address address = null;
        try {
            address = blockExplorer.getAddress(adres);
        } catch (APIException | IOException e) {
            e.printStackTrace();
        }

        //saldo portfela
        long finalBalance = address.getFinalBalance();
        if(finalBalance == 0){
            System.out.println("Saldo: 0\n");
        }
        else{
            System.out.println("Saldo: " + satoshiToBtc(finalBalance) + "\n");

        }

        //liczba transakcji portfela
        int liczbaTransakcji = address.getTxCount();
        System.out.println("Liczba transakcji portfela: " + liczbaTransakcji + "\n");

        //otrzymane BTC
        long otrzymane = address.getTotalReceived();
        if(otrzymane == 0){
            System.out.println("Otrzymane BTC: 0" + "\n");
        }
        else{
            System.out.println("Otrzymane BTC: " + satoshiToBtc(otrzymane) + "\n");
        }

        //wyslane BTC
        long wyslane = address.getTotalSent();
        if(wyslane == 0){
            System.out.println("Wyslane BTC: 0" + "\n");
        }
        else{
            System.out.println("Wyslane BTC: " + satoshiToBtc(wyslane) + "\n");
        }


        //transakcje danego portfela
        for (SingleTransaction t : address.getTransactions()) {
            System.out.println("Transakcja: " + t.getHash());

            //BTC wyslane
            System.out.println("Wyslane BTC\n");
            for (Input i : t.getInputs()) {
                long tmp = i.getPreviousOutput().getValue();
                System.out.println("Adres portfela: " + i.getPreviousOutput().getAddress());
                System.out.println("Ilosc BTC: " + satoshiToBtc(tmp) + "\n");
            }

            //BTC otrzymane po odjeciu oplat
            System.out.println("BTC otrzymane\n");
            for (Output i : t.getOutputs()) {
                long tmp = i.getValue();
                System.out.println("Adres portfela: " + i.getAddress());
                System.out.println("Ilosc BTC: " + satoshiToBtc(tmp) + "\n");
            }
            System.out.println("----------------------------------------\n");
        }
    }

    private static BigDecimal satoshiToBtc(final long satoshi) {
        final MathContext DEFAULT_CONTEXT = new MathContext(0, RoundingMode.UNNECESSARY);
        final int DEFAULT_SCALE = Coin.SMALLEST_UNIT_EXPONENT;
        final BigDecimal satoshiPerCoinDecimal = new BigDecimal(Coin.COIN.value, DEFAULT_CONTEXT);

        BigDecimal bdSatoshi = new BigDecimal(satoshi, DEFAULT_CONTEXT);
        return bdSatoshi.divide(satoshiPerCoinDecimal, DEFAULT_SCALE, RoundingMode.UNNECESSARY);
    }
}