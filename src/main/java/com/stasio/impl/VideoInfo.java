package com.stasio.impl;

import java.util.HashSet;

public class VideoInfo {
    private HashSet<String> tags;
    private String description;

    public VideoInfo(String descritpion, HashSet<String> tags) {
        this.description = descritpion;
        this.tags = tags;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }
}
