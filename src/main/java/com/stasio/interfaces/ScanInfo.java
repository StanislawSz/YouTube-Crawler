package com.stasio.interfaces;

import java.util.HashSet;
import java.util.LinkedList;

public interface ScanInfo {
    LinkedList<String> getLinks();
    HashSet<String> getTags();
    boolean isContainingWord(String[] WORDS);

}
