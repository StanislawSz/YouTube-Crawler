package com.stasio.interfaces;

import com.stasio.impl.VideoInfo;

import java.util.HashSet;
import java.util.LinkedList;

public interface ScanInfo {
    LinkedList<String> getLinks();
    HashSet<String> getTags();
    VideoInfo getVideoInfo();
    String getWallet();
    boolean isContainingWord(String[] WORDS);


}
