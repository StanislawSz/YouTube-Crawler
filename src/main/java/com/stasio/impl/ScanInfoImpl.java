package com.stasio.impl;

import com.stasio.interfaces.ScanInfo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanInfoImpl implements ScanInfo {

    private LinkedList<String> links;
    private VideoInfo videoInfo;

    public ScanInfoImpl(LinkedList<String> links, VideoInfo info) {
        this.links = links;
        this.videoInfo = info;
    }

    @Override
    public LinkedList<String> getLinks() {
        return links;
    }

    @Override
    public boolean isContainingWord(String[] WORDS) {
//        String description = videoInfo.getDescription();
//        for (String word : WORDS) {
//            if (description.toLowerCase().contains(word.toLowerCase())) {
//                return true;
//            }
//        }
//        return false;

        String description = videoInfo.getDescription();

        Pattern pattern=Pattern.compile("\\s+((bc1|[13])[a-zA-HJ-NP-Z0-9]{25,39})\\s+");
        Matcher matcher= pattern.matcher(description);

        return matcher.find();

    }

    @Override
    public HashSet<String> getTags() {
        return videoInfo.getTags();
    }

}
