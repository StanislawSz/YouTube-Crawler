package com.stasio.impl;

import com.stasio.interfaces.ScanInfo;
import org.jsoup.HttpStatusException;
import org.jsoup.UnsupportedMimeTypeException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.LinkedList;

public class CrawlerWorker implements Runnable {
    private String page;
    private CrawlerImpl crawler;
    private HashSet<String> links;
    private LinkedList<String> linksToVisit;
    private final String[] WORDS;

    public CrawlerWorker(String page, HashSet<String> links, String[] words, LinkedList<String> linksToVisit) {
        this.page = page;
        this.crawler = new CrawlerImpl();
        this.links = links;
        this.WORDS = words;
        this.linksToVisit = linksToVisit;
    }

    @Override
    public void run() {
        getPageLinks();
    }

    private void getPageLinks() {
        linksToVisit.add(page);

        while (links.size() < Main.MAX
                && !linksToVisit.isEmpty()
                && Main.linksWithMatch.size() < Main.EXPECTED_MATCH) {
            String currentPage;

            try {
                currentPage = nextUrl();
                ScanInfo info = crawler.scanPage("https://www.youtube.com" + currentPage);

                if (info.isContainingWord(WORDS)) {
                    System.out.println(currentPage);
                    Main.linksWithMatch.add(currentPage);
                }

                HashSet<String> tagsFromPage = info.getTags();

                if (tagsFromPage.removeAll(Main.TAGS) || tagsFromPage.size() == 0) {
                    linksToVisit.addAll(info.getLinks());
                }


            } catch (MalformedURLException e) {
                System.err.println(e.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + " : Niepoprawny adres strony");
            } catch (UnsupportedMimeTypeException | HttpStatusException e) {
                System.err.println(e.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + " : Niepoprawna zawartosc strony");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private synchronized String nextUrl() {
        String nextPage;
        do {
            nextPage = linksToVisit.remove(0);
        } while (links.contains(nextPage));
        links.add(nextPage);

        return nextPage;
    }
}
