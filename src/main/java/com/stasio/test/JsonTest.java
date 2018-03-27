package com.stasio.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonTest {
    public static void main(String[] args) {
        Path path = Paths.get("test");
        Path path2 = Paths.get("test2");
        String file="";
        String file2="";
        try {
            file = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            file2 = new String(Files.readAllBytes(path2), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();

        JSONArray json = new JSONObject(file)
                .getJSONObject("playerOverlays")
                .getJSONObject("playerOverlayRenderer")
                .getJSONObject("endScreen")
                .getJSONObject("watchNextEndScreenRenderer")
                .getJSONArray("results");

        for (int i=0; i<json.length(); i++) {
            try {
                String title = json.getJSONObject(i)
                        .getJSONObject("endScreenVideoRenderer")
                        .getJSONObject("navigationEndpoint")
                        .getJSONObject("commandMetadata")
                        .getJSONObject("webCommandMetadata")
                        .getString("url");
                System.out.println(title);
            } catch (JSONException e) {
                System.out.println("Nie odnaleziono obiektu Json podczas szukania linku");
            }
        }

        System.out.println();

        String komentarz = new JSONObject(file2).getJSONObject("videoDetails").getString("shortDescription");
        System.out.println(komentarz);
        System.out.println("Czas: " + (System.currentTimeMillis()-start));

        try {
            JSONArray jsona = new JSONObject(file2)
                    .getJSONObject("videoDetails")
                    .getJSONArray("keywords");

            for (int i = 0; i < jsona.length(); i++) {
                System.out.println(jsona.getString(i));
            }
        } catch (JSONException e) {
            System.out.println("Nie odnaleziono obiektu Json podczas szukania linku");
        }

        System.out.println(check(file2));
    }

    private static boolean check(String text) {
        String test = new JSONObject(text)
                .getJSONObject("videoDetails")
                .getString("shortDescription");

        return extractBitcoinAdress(test);


    }
    private static boolean extractBitcoinAdress(String description){
        Pattern pattern=Pattern.compile("\\s+((bc1|[13])[a-zA-HJ-NP-Z0-9]{25,39})\\s+");
        Matcher matcher= pattern.matcher(description);
        return matcher.find();
    }
}
