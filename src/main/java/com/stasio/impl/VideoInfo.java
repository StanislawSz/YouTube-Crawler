package com.stasio.impl;

import java.util.HashSet;
import java.util.stream.Collectors;

public class VideoInfo {
    private HashSet<String> tags;
    private String description;
    private String title;
    private String author;


    public VideoInfo(String description, HashSet<String> tags, String title, String author) {
        this.tags = tags;
        this.description = description;
        this.title = title;
        this.author = author;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    public String getStringTags() {
        return tags.stream().collect(Collectors.joining(","));
    }
}
