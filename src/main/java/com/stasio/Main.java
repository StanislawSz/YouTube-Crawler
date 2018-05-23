package com.stasio;


import com.stasio.impl.CrawlerImpl;
import com.stasio.impl.CrawlerWorker;
import com.stasio.interfaces.Crawler;
import com.stasio.interfaces.ScanInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@SpringBootApplication
public class Main {
    // Zbior sprawdzonych linkowa
    private static HashSet<String> links = new HashSet<>();
    private static LinkedList<String> linksToVisit = new LinkedList<>();
    public static HashSet<String> linksWithMatch = new HashSet<>();
    public static final HashSet<String> TAGS = new HashSet<>();


    public static final int MAX = 60000;
    public static final int EXPECTED_MATCH = 30000;
    private static final int THREADS = 2;


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        runCrawler();
//        test();


    }

    private static void runCrawler() {
        String URL = "https://www.youtube.com/watch?v=BpICc5dfuNg";

        String[] words = {"bitcoin", "btc"};

        fillTags();

        Crawler crawler = new CrawlerImpl();
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        final long START = System.currentTimeMillis();

        try {
            ScanInfo scanInfo = crawler.scanPage(URL);
//            LinkedList<String> linksOnPage = scanInfo.getLinks();
            linksToVisit = scanInfo.getLinks();

            for (int i = 0; i < THREADS; i++) {
                pool.execute(new CrawlerWorker(linksToVisit.remove(0), links, words, linksToVisit));
            }

            pool.shutdown();

            if (pool.awaitTermination(1, TimeUnit.HOURS)) {
                System.out.println("-----------------------------");
                linksWithMatch.forEach(System.out::println);

                System.out.println("\nPrzeskanowano: " + links.size() + " stron w czasie " + (System.currentTimeMillis() - START) / 1000 + "s");
                System.out.println("Znaleziono: " + linksWithMatch.size() + " trafien");
                System.out.println("Links to visit: " + linksToVisit.size());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void fillTags() {
        String fileName = "tags.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(TAGS::add);
        } catch (IOException e) {
            System.err.println("Main.fillTags() : Problem z odczytaniem tagow z pliku .txt");
        }
    }

}
