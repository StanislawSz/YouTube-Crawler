package com.stasio.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.LinkedList;

public class JsonReader {

    public LinkedList<String> linksOnPage(String jsonText) {
        LinkedList<String> links = new LinkedList<>();

        try {
            JSONArray json = new JSONObject(jsonText)
                    .getJSONObject("playerOverlays")
                    .getJSONObject("playerOverlayRenderer")
                    .getJSONObject("endScreen")
                    .getJSONObject("watchNextEndScreenRenderer")
                    .getJSONArray("results");

            for (int i = 0; i < json.length(); i++) {
                //Zwykłe linki
                try {
                    String link = json.getJSONObject(i)
                            .getJSONObject("endScreenVideoRenderer")
                            .getJSONObject("navigationEndpoint")
                            .getJSONObject("commandMetadata")
                            .getJSONObject("webCommandMetadata")
                            .getString("url");
                    links.add(link);
                } catch (JSONException e) {
                    //Linki do playlist
                    try {
                        String link = json.getJSONObject(i)
                                .getJSONObject("endScreenPlaylistRenderer")
                                .getJSONObject("navigationEndpoint")
                                .getJSONObject("commandMetadata")
                                .getJSONObject("webCommandMetadata")
                                .getString("url");
                        links.add(link);
                    } catch (JSONException ex) {
                        System.err.println(e.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + ".linksOnPage(): Nie udalo sie odnalezc linku");
                    }
                }
            }
        } catch (JSONException ex) {
            System.err.println(ex.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + ".linksOnPage(): Nie udalo sie odczytac JSON'a");
        }

        return links;
    }

    public String videoDescription(String jsonText) {
        String description = "";
        try {
            description = new JSONObject(jsonText)
                    .getJSONObject("videoDetails")
                    .getString("shortDescription");
        } catch (JSONException ex) {
            System.err.println(ex.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + ".videoDescription(): Nie udalo sie otworzyc opisu filmu");
        }

        return description;
    }

    public HashSet<String> videoTags(String jsonText) {
//        String tags = "";
        HashSet<String> tags = new HashSet<>();
        try {
            JSONArray json = new JSONObject(jsonText)
                    .getJSONObject("videoDetails")
                    .getJSONArray("keywords");

            for (int i = 0; i < json.length(); i++) {
//                tags += json.getString(i) + ",";
                tags.add(json.getString(i).toLowerCase());
            }
        } catch (JSONException e) {
            System.err.println(e.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + ": Brak tagow na stronie");
        }

        return tags;
    }

    public String videoTitle(String jsonText) {
        String title = "";
        try {
            title = new JSONObject(jsonText)
                    .getJSONObject("videoDetails")
                    .getString("title");
        } catch (JSONException ex) {
            System.err.println(ex.getClass().getSimpleName() + " w " + this.getClass().getSimpleName() + ".videoDescription(): Nie udalo sie otworzyc opisu filmu");
        }

        System.out.println(title);
        return title;
    }
}
