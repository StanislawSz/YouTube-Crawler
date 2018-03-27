package com.stasio.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String bodyText = " window[\"ytInitialData\"] = {\"responseContext\":{\"serviceTrackingParams\":[{\"service\":\"GFEEDBACK\",\"params\":[{\"key\":\"has_unlimited_entitlement\",\"value\":\"False\"},{\"key\":\"has_unlimited_ncc_free_trial\",\"value\":\"False\"},{\"key\":\"e\",\"value\":\"23708904,23708906,23708910,23710476,23712283,23713711,23716256,23721593,23721699,23721898,23722414,23723208,23723618,23726171,23727194,23729572,23729690,23729713,23730604,23730641,23731204,23731309}; window[\"ytInitialPlayerResponse\"] = (\n" +
                "        {\"responseContext\":{\"serviceTrackingParams\"}}+wy%C5%9Bwietlenia\\u0026length_seconds=669\\u0026author=TVGRYpl\"}};\n" +
                "    window[\"ytInitialPlayerResponse\"";

        String json;

        long start = System.currentTimeMillis();
        json = bodyText.substring(bodyText.indexOf("{\"responseContext"), bodyText.lastIndexOf("window[")-6);
        System.out.println(json);

//        try(FileOutputStream fos = new FileOutputStream("jsonZeStrony.txt");
//            PrintWriter pw = new PrintWriter(fos)) {
//            pw.println(json);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(bodyText.contains("InitialDa"));
        HashSet<String> lista = new HashSet<>();
        lista.add("a");
        lista.add("b");
        lista.add("c");

        HashSet<String> lista2 = new HashSet<>();
//        lista2.add("a");
//        lista2.add("h");
//        System.out.println(lista.removeAll(lista2));

//        lista.forEach(i -> System.out.println(i));
//        lista2.forEach(i -> System.out.println(i));

//        String test = "Donate Bitcoin: 3NqhJSAikoFiYmZm3ACGzdw9Lr86ZiLT7K\\nSupport the Show: https://www.patreon.com/madbitcoins\\n\\nListen to WCN Audio Podcasts: \\nhttps://itunes.apple.com/us/podcast/world-crypto-network/id825708806?mt=2\\n\\nCheck out the brand new http://WorldCryptoNetwork.com/\\n\\nFollow WCN on Twitter:  https://twitter.com/WorldCryptoNet\\n\\nTrack the Mayer Multiple on WCN: https://www.worldcryptonetwork.com/price\\n\\n\u200E9214.14 USD / BTC - Average United States Dollar Bitcoin Price\\nhttps://bitcoinaverage.com/en/bitcoin-price/btc-to-usd\\nhttp://www.btcsatoshi.com/\\n\\nBTC-USD Price | World Crypto Network\\nhttps://www.worldcryptonetwork.com/price\\n\\nInvestors bullish on bitcoin now that the 'Tokyo Whale' has stopped selling\\nhttps://www.cnbc.com/2018/03/12/investors-bullish-on-bitcoin-now-that-the-tokyo-whale-has-stopped-selling.html\\n\\nBitcoin Price Will Hit $1 Million by 2020 Says John McAfee | Investopedia\\nhttps://www.investopedia.com/news/mcafee-tracker-predicts-1-bitcoin1m-2020/\\n\\nAnother Exchange Jumps on Bitcoin Bandwagon - WSJ\\nhttps://www.wsj.com/articles/another-exchange-jumps-on-bitcoin-bandwagon-1520868955\\n\\nStudy Finds $3B Worth of Faked Cryptocurrency Volumes and Wash Trades - Bitcoin News\\nhttps://news.bitcoin.com/study-finds-3b-worth-of-faked-cryptocurrency-volumes-and-wash-trades/\\n\\nThe 1130ET Bitcoin-Battering Continues | Zero Hedge\\nhttps://www.zerohedge.com/news/2018-03-12/1130et-bitcoin-battering-continues\\n\\nWe Need To Shut Bitcoin And All Other Cryptocurrencies Down. Here's Why.\\nhttps://www.forbes.com/sites/jasonbloomberg/2018/03/10/we-need-to-shut-bitcoin-and-all-other-cryptocurrencies-down-heres-why/#61cfeafc1bca\\n\\nBlockchain Technology Talk is Largely Nonsense - Bitcoin News\\nhttps://news.bitcoin.com/blockchain-technology-talk-is-largely-nonsense/\\n\\ndrinomarino on Twitter: \\\"First bitcoin monument!… \\\"\\nhttps://twitter.com/jernejdrinovec/status/973132095097851904\\n\\nPomp  on Twitter: \\\"The first Bitcoin monument has been erected in Slovenia at a traffic circle. (H/t @jernejdrinovec)… \\\"\\nhttps://twitter.com/APompliano/status/973227088374362113\\n\\nArmin van Bitcoin  on Twitter: \\\"All of my friends talk about running their own business one day, but just can't get the time for it now. You see, after work, they need some time to watch hours of TV shows, Netflix, sports and play video games. https://t.co/I1gxxdVJYF\\\"\\nhttps://twitter.com/ArminVanBitcoin/status/973237682234167296\\n\\nMMXVIII on Twitter: \\\"All the money in the world can't buy you a soul  \\\"\\nhttps://twitter.com/dblmsey/status/972936260904878081\\n\\nMad Bitcoins on Twitter: \\\"What the hell? #vegas “Tell those douchebags at coinbase to bite you.” #bitcoin… \\\"\\nhttps://twitter.com/MadBitcoins/status/972915439784288256\\n\\nMad Bitcoins on Twitter: \\\"Always invest responsibly. Warning. The crypto currency house of cards may fall as fast a stripper’s g-string. We cannot confirm not deny that those associated with Coinbase are douchebags but if it looks like a duck, quacks like a duck, it’s probably a duck. #bitcoin… https://t.co/j4U4h1jWuO\\\"\\nhttps://twitter.com/MadBitcoins/status/972916044984561664\\n\\nMad Bitcoins on Twitter: \\\"Not that I’m looking forward to eating at the Heart Attack Grill (where they promise three hard spankings if you don’t finish your meal and all customers are required to wear hospital gowns) but they do have a #bitcoin atm.… https://t.co/1fEEMUNUZj\\\"\\nhttps://twitter.com/MadBitcoins/status/972917037436567552\\n\\nTaylor Good on Twitter: \\\". Worth adding the billboard I just saw in Vegas..… \\\"\\nhttps://twitter.com/taylorgood/status/972932837128134656\\n\\nMad Bitcoins on Twitter: \\\"#bitcoin #vegas… \\\"\\nhttps://twitter.com/MadBitcoins/status/972918167499456512\\n\\nMad Bitcoins on Twitter: \\\"Hey hey #Vegas! Join us Tuesday at 7:00 pm at Nacho Daddy (113 N. 4th St.) Near Fremont St. Experience and The D Hotel. #bitcoin… https://t.co/6wJkKue5yh\\\"\\nhttps://twitter.com/MadBitcoins/status/972990286375759872";
//        System.out.println(extractBitcoinAdress(test));

        System.out.println(Runtime.getRuntime().totalMemory());

    }


    private static boolean extractBitcoinAdress(String description){
        Pattern bitcoin=Pattern.compile("\\s+((bc1|[13])[a-zA-HJ-NP-Z0-9]{25,39})(\\s+)|(\\n)");
        Matcher matcher= bitcoin.matcher(description);
        return matcher.find();
    }
}
