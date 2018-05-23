package com.stasio.impl;

import com.stasio.interfaces.Crawler;
import com.stasio.interfaces.ScanInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class CrawlerImpl implements Crawler {
    private Document document;
    private JsonReader jsonReader;


    public CrawlerImpl() {
        jsonReader = new JsonReader();
    }

    public ScanInfo scanPage(String URL) throws IOException {
//        System.setProperty("javax.net.ssl.trustStore",path);
        String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
        document = Jsoup.connect(URL).userAgent(USER_AGENT).get();
        return new ScanInfoImpl(findLinks(), findVideoInfo());
    }

    private LinkedList<String> findLinks() {
        String bodyText = document.body().data();
        String jsonText = "";
        try {
            jsonText = bodyText.substring(bodyText.indexOf("{\"responseContext"), bodyText.lastIndexOf("window[") - 6);
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println(e.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + ".findLinks(): Nie udalo sie wyodrebnic JSON ze strony");
        }

        return jsonReader.linksOnPage(jsonText);
    }

    private VideoInfo findVideoInfo() {
        String bodyText = document.body().data();
        String jsonText = "";
        try {
            jsonText = bodyText.substring(bodyText.lastIndexOf("{\"responseContext"), bodyText.lastIndexOf("}});") + 2);
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println(e.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + ".findText(): Nie udalo sie wyodrebnic JSON ze strony");
        }

        String description = jsonReader.videoDescription(jsonText);
        HashSet<String> tags = jsonReader.videoTags(jsonText);
        String title = jsonReader.videoTitle(jsonText);
        String author = jsonReader.videoAuthor(jsonText);

        return new VideoInfo(description, tags, title, author);
    }

}
